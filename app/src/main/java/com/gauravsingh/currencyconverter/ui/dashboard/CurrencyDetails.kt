package com.gauravsingh.currencyconverter.ui.dashboard

import com.gauravsingh.currencyconverter.data.model.Currency

data class CurrencyDetails(
    val currency: Currency,
    val rate: String,
    val amount: String
)