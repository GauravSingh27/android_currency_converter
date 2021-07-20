package com.gauravsingh.currencyconverter.data.source.repository

import android.content.SharedPreferences
import com.gauravsingh.currencyconverter.data.model.Currency
import com.gauravsingh.currencyconverter.data.model.CurrencyQuote
import com.gauravsingh.currencyconverter.data.source.local.LocalDataSource
import com.gauravsingh.currencyconverter.data.source.remote.RemoteDataSource
import com.gauravsingh.currencyconverter.network.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val coroutineDispatcherIO: CoroutineDispatcher,
    private val sharedPreferences: SharedPreferences
) : Repository {

    override suspend fun getCurrencyList(): Result<List<Currency>> {
        return withContext(coroutineDispatcherIO) {
            when (val response = remoteDataSource.getCurrencyList()) {
                is Result.Success -> {
                    localDataSource.insertCurrencies(response.data)
                    localDataSource.getCurrencyList()
                }
                else -> response
            }
        }
    }

    override suspend fun getCurrencyQuotes(sourceCurrencyCode: String): Result<List<CurrencyQuote>> {
        return withContext(coroutineDispatcherIO) {
            when (val response = remoteDataSource.getCurrencyQuotes(sourceCurrencyCode)) {
                is Result.Success -> {
                    localDataSource.insertCurrencyQuotes(response.data)
                    localDataSource.getCurrencyQuotes(sourceCurrencyCode)
                }
                else -> response
            }
        }
    }
}