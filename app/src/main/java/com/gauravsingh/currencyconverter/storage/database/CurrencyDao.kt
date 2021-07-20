package com.gauravsingh.currencyconverter.storage.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gauravsingh.currencyconverter.data.model.Currency
import com.gauravsingh.currencyconverter.data.model.CurrencyQuote

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencies(currencies: List<Currency>)

    @Query("SELECT * FROM currency")
    fun getCurrencyList(): List<Currency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencyQuote(quotes: List<CurrencyQuote>)

    @Query("SELECT * FROM currency_quote WHERE fromAndToCurrencyCode LIKE :sourceCountryCode")
    fun getCurrencyQuotes(sourceCountryCode: String): List<CurrencyQuote>
}