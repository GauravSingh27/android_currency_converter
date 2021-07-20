package com.gauravsingh.currencyconverter.data.source.local

import com.gauravsingh.currencyconverter.data.model.Currency
import com.gauravsingh.currencyconverter.data.model.CurrencyQuote
import com.gauravsingh.currencyconverter.network.Result

interface LocalDataSource {

    suspend fun insertCurrencies(currencies: List<Currency>)

    suspend fun getCurrencyList(): Result<List<Currency>>

    suspend fun insertCurrencyQuotes(currencyQuotes: List<CurrencyQuote>)

    suspend fun getCurrencyQuotes(sourceCountryCode: String): Result<List<CurrencyQuote>>
}