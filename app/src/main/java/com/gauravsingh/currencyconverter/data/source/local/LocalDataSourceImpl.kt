package com.gauravsingh.currencyconverter.data.source.local

import com.gauravsingh.currencyconverter.data.model.Currency
import com.gauravsingh.currencyconverter.data.model.CurrencyQuote
import com.gauravsingh.currencyconverter.network.Result
import com.gauravsingh.currencyconverter.storage.database.CurrencyDao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val currencyDao: CurrencyDao
) : LocalDataSource {

    override suspend fun insertCurrencies(currencies: List<Currency>) {
        return currencyDao.insertCurrencies(currencies)
    }

    override suspend fun getCurrencyList(): Result<List<Currency>> {
        return Result.Success(currencyDao.getCurrencyList())
    }

    override suspend fun insertCurrencyQuotes(currencyQuotes: List<CurrencyQuote>) {
        return currencyDao.insertCurrencyQuote(currencyQuotes)
    }

    override suspend fun getCurrencyQuotes(sourceCountryCode: String): Result<List<CurrencyQuote>> {
        return Result.Success(currencyDao.getCurrencyQuotes("$sourceCountryCode%"))
    }
}