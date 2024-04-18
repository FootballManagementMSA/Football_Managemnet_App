package com.sejongunivclub.core.di

import android.content.Context
import android.os.Build
import android.view.WindowManager
import com.sejongunivclub.core.model.LocalScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalSizeModule {

    @Provides
    @Singleton
    fun provideLocalScreenSize(@ApplicationContext context: Context) : LocalScreen {
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val (w, h) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            manager.currentWindowMetrics.bounds.width() to manager.currentWindowMetrics.bounds.height()
        } else {
            manager.defaultDisplay.width to manager.defaultDisplay.height
        }
        return LocalScreen(w.toDouble(), h.toDouble())
    }

    @Provides
    @Singleton // 임시 API 나오면 제거 예정
    fun provideLocalScreenSizeTemp(@ApplicationContext context: Context) : com.sejongunivclub.network_api.entity.RemoteScreen {
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val (w, h) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            manager.currentWindowMetrics.bounds.width() to manager.currentWindowMetrics.bounds.height()
        } else {
            manager.defaultDisplay.width to manager.defaultDisplay.height
        }
        return com.sejongunivclub.network_api.entity.RemoteScreen(w.toDouble(), h.toDouble())
    }
}