package com.gauravsingh.currencyconverter.network

import com.gauravsingh.currencyconverter.network.model.CurrencyListResponse
import com.gauravsingh.currencyconverter.network.model.CurrencyQuoteResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("list")
    suspend fun getCurrencies(
        @Query("access_key") accessKey: String
    ): CurrencyListResponse

    @GET("live")
    suspend fun getCurrencyQuotes(
        @Query("access_key") accessKey: String
    ): CurrencyQuoteResponse
}