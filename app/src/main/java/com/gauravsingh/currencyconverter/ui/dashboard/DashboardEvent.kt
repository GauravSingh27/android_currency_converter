package com.gauravsingh.currencyconverter.ui.dashboard

sealed class DashboardEvent {

    object InProgress : DashboardEvent()
    data class Error(val message: String) : DashboardEvent()
}

