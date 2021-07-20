package com.gauravsingh.currencyconverter.network

import com.google.gson.annotations.SerializedName

abstract class ApiResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("terms")
    val terms: String,
    @SerializedName("privacy")
    val privacy: String
)