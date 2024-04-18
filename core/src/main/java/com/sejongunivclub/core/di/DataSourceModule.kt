package com.sejongunivclub.core.di

import com.sejongunivclub.core.datasource.ClubDataSource
import com.sejongunivclub.core.datasource.ClubDataSourceImpl
import com.sejongunivclub.core.datasource.MainHomeDataSource
import com.sejongunivclub.core.datasource.MainHomeDataSourceImpl
import com.sejongunivclub.core.datasource.PositionPresetDataSource
import com.sejongunivclub.core.datasource.PositionPresetDataSourceImpl
import com.sejongunivclub.core.datasource.SquadDataSource
import com.sejongunivclub.core.datasource.SquadDataSourceImpl
import com.sejongunivclub.core.datasource.UserLocalDataSource
import com.sejongunivclub.core.datasource.UserLocalDataSourceImpl
import com.sejongunivclub.core.datasource.UserRemoteDataSource
import com.sejongunivclub.core.datasource.UserRemoteDataSourceImpl
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