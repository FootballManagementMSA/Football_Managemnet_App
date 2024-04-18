package com.sejongunivclub.footballmanager_pj

import NotificationActionReceiver
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("FirebaseMessaging", "From: ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {
            Log.d("FirebaseMessaging", "Message data payload: ${remoteMessage.data}")
            handleDataMessage(remoteMessage.data)
        }

        remoteMessage.notification?.let {

            val match_title=remoteMessage.data["title"]?:"일정 제목 없음"
            val latitude = remoteMessage.data["latitude"] ?: "No Latitude"
            sendNotification(it.title ?: "Title", it.body ?: "Body", latitude,match_title)
        }
    }

    // 데이터 메시지 처리
    private fun handleDataMessage(data: Map<String, String>) {
        val latitude = data["latitude"] ?: "No Latitude"
        val match_title=data["title"]?:"제목 없음"
        sendNotification("Data Message", "Latitude is: $latitude", latitude,match_title)
    }

    // 알림을 전송
    private fun sendNotification(title: String, messageBody: String, latitude: String?,match_title: String) {
        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val modifiedMessageBody = "$messageBody - Latitude: $latitude $match_title"

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(modifiedMessageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)

        val acceptIntent = Intent(this, NotificationActionReceiver::class.java).apply {
            action = "ACCEPT_ACTION"
        }
        val acceptPendingIntent = PendingIntent.getBroadcast(this, 0, acceptIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val declineIntent = Intent(this, NotificationActionReceiver::class.java).apply {
            action = "DECLINE_ACTION"
        }
        val declinePendingIntent = PendingIntent.getBroadcast(this, 1, declineIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        notificationBuilder.addAction(R.drawable.ic_launcher_background, "일정 수락", acceptPendingIntent)
        notificationBuilder.addAction(R.drawable.ic_launcher_background, "일정 거절", declinePendingIntent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).also {
                it.description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }
}
