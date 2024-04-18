package com.sejongunivclub.coroutine

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class NetworkingScope

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ComputingScope

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class UIScope