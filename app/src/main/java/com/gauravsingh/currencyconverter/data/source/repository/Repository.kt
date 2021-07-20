package com.gauravsingh.currencyconverter.data.source.repository

import com.gauravsingh.currencyconverter.data.model.Currency
import com.gauravsingh.currencyconverter.data.model.CurrencyQuote
import com.gauravsingh.currencyconverter.network.Result

interface Repository {

    suspend fun getCurrencyList(): Result<List<Currency>>

    suspend fun getCurrencyQuotes(sourceCurrencyCode: String): Result<List<CurrencyQuote>>
}