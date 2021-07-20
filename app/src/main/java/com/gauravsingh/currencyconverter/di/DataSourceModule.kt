package com.gauravsingh.currencyconverter.di

import com.gauravsingh.currencyconverter.data.source.local.LocalDataSource
import com.gauravsingh.currencyconverter.data.source.local.LocalDataSourceImpl
import com.gauravsingh.currencyconverter.data.source.remote.RemoteDataSource
import com.gauravsingh.currencyconverter.data.source.remote.RemoteDataSourceImpl
import com.gauravsingh.currencyconverter.data.source.repository.Repository
import com.gauravsingh.currencyconverter.data.source.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRepository(repository: RepositoryImpl): Repository

    @Binds
    abstract fun bindLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource
}