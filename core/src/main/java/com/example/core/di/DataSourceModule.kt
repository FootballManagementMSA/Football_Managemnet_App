package com.example.core.di

import com.example.core.datasource.ClubDataSource
import com.example.core.datasource.ClubDataSourceImpl
import com.example.core.datasource.MainHomeDataSource
import com.example.core.datasource.MainHomeDataSourceImpl
import com.example.core.datasource.PositionPresetDataSource
import com.example.core.datasource.PositionPresetDataSourceImpl
import com.example.core.datasource.SquadDataSource
import com.example.core.datasource.SquadDataSourceImpl
import com.example.core.datasource.UserLocalDataSource
import com.example.core.datasource.UserLocalDataSourceImpl
import com.example.core.datasource.UserRemoteDataSource
import com.example.core.datasource.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

    @Binds
    abstract fun bindsUserDataSource(userLocalDataSourceImpl: UserLocalDataSourceImpl): UserLocalDataSource

    @Binds
    abstract fun bindsUserRemoteDataSource(userRemoteDataSourceImpl: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    abstract fun bindsPositionPresetDataSource(positionPresetDataSourceImpl: PositionPresetDataSourceImpl): PositionPresetDataSource

    @Binds
    abstract fun bindsMainHomeDataSource(mainHomeDataSourceImpl: MainHomeDataSourceImpl): MainHomeDataSource

    @Binds
    abstract fun bindsClubDataSource(clubDataSourceImpl: ClubDataSourceImpl): ClubDataSource

    @Binds
    abstract fun bindsSquadDataSource(squadDataSourceImpl: SquadDataSourceImpl): SquadDataSource
}