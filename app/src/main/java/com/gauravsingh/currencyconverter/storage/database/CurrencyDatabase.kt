package com.gauravsingh.currencyconverter.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gauravsingh.currencyconverter.data.model.Currency
import com.gauravsingh.currencyconverter.data.model.CurrencyQuote

@Database(
    entities = [
        Currency::class,
        CurrencyQuote::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun getCurrencyDao(): CurrencyDao
}