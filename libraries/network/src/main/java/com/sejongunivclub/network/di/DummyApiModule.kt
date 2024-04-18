package com.sejongunivclub.network.di

import com.sejongunivclub.network.network.TestApi
import com.sejongunivclub.network.network.TestApiImpl
import com.sejongunivclub.network.repository.ClubRepositoryImpl
import com.sejongunivclub.network.repository.SquadRepositoryImpl
import com.sejongunivclub.network.repository.TempSquadRepositoryImpl
import com.sejongunivclub.network.repository.TestRepositoryImpl
import com.sejongunivclub.network.repository.UserRepositoryImpl
import com.sejongunivclub.network_api.repository.ClubRepository
import com.sejongunivclub.network_api.repository.SquadRepository
import com.sejongunivclub.network_api.repository.TempSquadRepository
import com.sejongunivclub.network_api.repository.TestRepository
import com.sejongunivclub.network_api.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DummyApiModule {
    @Binds
    abstract fun bindsDummy(testApiImpl: TestApiImpl): TestApi

    @Binds
    abstract fun bindsTestRepository(testRepositoryImpl: TestRepositoryImpl): TestRepository

    @Binds
    abstract fun bindsTempSquadRepository(tempSquadRepositoryImpl: TempSquadRepositoryImpl): TempSquadRepository

    @Binds
    abstract fun bindsUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindsClubRepository(clubRepositoryImpl: ClubRepositoryImpl): ClubRepository

    @Binds
    abstract fun bindsSquadRepository(squadRepositoryImpl: SquadRepositoryImpl): SquadRepository
}