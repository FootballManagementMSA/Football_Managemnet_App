package com.sejongunivclub.core.di

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sejongunivclub.core.util.BASE_URL
import com.sejongunivclub.core.util.NAVER_URL
import com.sejongunivclub.core.util.userDataStore
import com.sejongunivclub.network_api.api.ClubApi
import com.sejongunivclub.network_api.api.FootballManagerApi
import com.sejongunivclub.network_api.api.MainHomeApi
import com.sejongunivclub.network_api.api.NaverApi
import com.sejongunivclub.network_api.api.SquadApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideInterceptor(@ApplicationContext context: Context): Interceptor {

        return Interceptor { chain: Interceptor.Chain ->
            runBlocking {
                val key = stringPreferencesKey("ACEESS_TOKEN")
                val accessToken: String = context.userDataStore.data.first()[key] ?: ""
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $accessToken")
                    .build()
                chain.proceed(newRequest)
            }
        }
    }
    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
//            addInterceptor(httpLoggingInterceptor)
            addInterceptor(interceptor)
            // authenticator(tokenAuthenticator)
        }.build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named(NAVER_URL)
    fun provideRetrofitForBaseUrl2(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(NAVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideNaverApi(@Named(NAVER_URL) retrofit: Retrofit): NaverApi {
        return retrofit.create(NaverApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFootballManagerApi(retrofit: Retrofit): FootballManagerApi {
        return retrofit.create(FootballManagerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMainHomeApi(retrofit: Retrofit): MainHomeApi {
        return retrofit.create(MainHomeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideClubApi(retrofit: Retrofit): ClubApi {
        return retrofit.create(ClubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSquadApi(retrofit: Retrofit): SquadApi {
        return retrofit.create(SquadApi::class.java)
    }
}