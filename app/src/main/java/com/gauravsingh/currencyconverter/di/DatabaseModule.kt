package com.gauravsingh.currencyconverter.di

import android.content.Context
import androidx.room.Room
import com.gauravsingh.currencyconverter.storage.database.CurrencyDao
import com.gauravsingh.currencyconverter.storage.database.CurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CurrencyDatabase {
        return Room.databaseBuilder(
            context,
            CurrencyDatabase::class.java,
            "currency_database.db"
        ).build()
    }

    @Provides
    fun provideCurrencyDao(database: CurrencyDatabase) : CurrencyDao {
        return database.getCurrencyDao()
    }
}