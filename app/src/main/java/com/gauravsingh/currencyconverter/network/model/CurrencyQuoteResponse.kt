package com.gauravsingh.currencyconverter.network.model

import com.gauravsingh.currencyconverter.network.ApiResponse
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class CurrencyQuoteResponse(
    success: Boolean,
    terms: String,
    privacy: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("quotes")
    val quotes: JsonObject
) : ApiResponse(success, terms, privacy)