package com.gauravsingh.currencyconverter.network.model

import com.gauravsingh.currencyconverter.network.ApiResponse
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class CurrencyListResponse(
    success: Boolean,
    terms: String,
    privacy: String,
    @SerializedName("currencies")
    val currencies: JsonObject
) : ApiResponse(success, terms, privacy)