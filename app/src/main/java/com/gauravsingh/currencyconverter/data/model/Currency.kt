package com.gauravsingh.currencyconverter.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
class Currency(
    @PrimaryKey
    @ColumnInfo(name = "currencyCode")
    val currencyCode: String,
    @ColumnInfo(name = "currencyName")
    val currencyName: String
)