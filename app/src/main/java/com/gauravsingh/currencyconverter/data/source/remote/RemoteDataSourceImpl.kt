package com.gauravsingh.currencyconverter.data.source.remote

import com.gauravsingh.currencyconverter.data.model.Currency
import com.gauravsingh.currencyconverter.data.model.CurrencyQuote
import com.gauravsingh.currencyconverter.network.CurrencyService
import com.gauravsingh.currencyconverter.network.Result
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val currencyService: CurrencyService
) : RemoteDataSource {

    override suspend fun getCurrencyList(): Result<List<Currency>> {

        return try {
            currencyService.getCurrencies("be49e11f3dc99923da687187200ff313").let { response ->
                Result.Success(
                    response.currencies.entrySet().map { Currency(it.key, it.value.toString()) }
                )
            }
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }

    override suspend fun getCurrencyQuotes(sourceCurrencyCode: String): Result<List<CurrencyQuote>> {
        return try {
            currencyService.getCurrencyQuotes(
                "be49e11f3dc99923da687187200ff313"
            ).let { response ->
                Result.Success(
                    response.quotes.entrySet().map {
                        CurrencyQuote(
                            sourceCurrencyCode + it.key.removePrefix("USD"),
                            BigDecimal(it.value.toString()).divide(
                                BigDecimal(
                                    response.quotes.get("USD$sourceCurrencyCode").toString()
                                )
                                , 2, RoundingMode.HALF_UP).toString()
                        )

                    }
                )
            }
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }
}