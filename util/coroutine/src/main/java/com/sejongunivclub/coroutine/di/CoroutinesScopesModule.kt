package com.sejongunivclub.coroutine.di

import com.sejongunivclub.coroutine.ComputingScope
import com.sejongunivclub.coroutine.DefaultDispatcher
import com.sejongunivclub.coroutine.IoDispatcher
import com.sejongunivclub.coroutine.MainDispatcher
import com.sejongunivclub.coroutine.NetworkingScope
import com.sejongunivclub.coroutine.UIScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesScopesModule {

    @Provides
    @Singleton
    @NetworkingScope
    fun providesNetworkingScope(
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + ioDispatcher)

    @Provides
    @Singleton
    @ComputingScope
    fun providesComputingScope(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

    @Provides
    @Singleton
    @UIScope
    fun providesUIScope(
        @MainDispatcher mainDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + mainDispatcher)
}