package com.gauravsingh.currencyconverter.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_quote")
class CurrencyQuote(
    @PrimaryKey
    @ColumnInfo(name = "fromAndToCurrencyCode")
    val fromAndToCurrencyCode: String,
    @ColumnInfo(name = "rate")
    val rate: String
)