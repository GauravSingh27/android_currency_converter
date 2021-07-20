package com.gauravsingh.currencyconverter.ui.dashboard

import android.icu.math.BigDecimal
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gauravsingh.currencyconverter.data.model.Currency
import com.gauravsingh.currencyconverter.data.model.CurrencyQuote
import com.gauravsingh.currencyconverter.data.source.repository.Repository
import com.gauravsingh.currencyconverter.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val currencyCodes: LiveData<List<String>> by lazy { _currencyCodes }
    private val _currencyCodes: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }

    val currencyDetails: LiveData<List<CurrencyDetails>> by lazy { _currencyDetails }
    private val _currencyDetails: MutableLiveData<List<CurrencyDetails>> by lazy {
        MutableLiveData<List<CurrencyDetails>>()
    }

    val event: LiveData<DashboardEvent> by lazy { _event }
    private val _event: MutableLiveData<DashboardEvent> by lazy { MutableLiveData<DashboardEvent>() }

    private lateinit var currencies: List<Currency>

    private val noSelection = "--"

    fun fetchCurrencies() {

        _event.postValue(DashboardEvent.InProgress)
        viewModelScope.launch {
            when (val result = repository.getCurrencyList()) {

                is Result.Success -> {
                    currencies = result.data
                    _currencyCodes.postValue(extractCurrencyCodes(currencies))
                }

                is Result.Error -> _event.postValue(
                    DashboardEvent.Error(result.exception.localizedMessage.orEmpty())
                )
            }
        }

    }

    fun fetchCurrencyQuotes(sourceCountryCode: String, amount: String?) {


        _event.postValue(DashboardEvent.InProgress)
        viewModelScope.launch {
            when (val result = repository.getCurrencyQuotes(sourceCountryCode)) {

                is Result.Success -> _currencyDetails.postValue(
                    convertToCurrencyDetails(
                        result.data,
                        sourceCountryCode,
                        amount
                    )
                )

                is Result.Error -> _event.postValue(
                    DashboardEvent.Error(result.exception.localizedMessage.orEmpty())
                )
            }
        }
    }

    private fun convertToCurrencyDetails(
        currencyQuote: List<CurrencyQuote>,
        sourceCountryCode: String,
        amount: String?
    ): List<CurrencyDetails> {

        val currencyDetails = mutableListOf<CurrencyDetails>()

        currencyQuote.forEach { quote ->
            currencies.find {
                it.currencyCode == quote.fromAndToCurrencyCode.removePrefix(sourceCountryCode)
            }?.let { currency ->

                val convertedAmount = amount.takeUnless{ it.isNullOrBlank() }?.let {
                    BigDecimal(it).multiply(BigDecimal(quote.rate)).toString()
                } ?: noSelection
                currencyDetails.add(CurrencyDetails(currency, quote.rate, convertedAmount))
            }

        }
        return currencyDetails
    }

    private fun extractCurrencyCodes(currencies: List<Currency>): List<String> {
        val currencyCodes = mutableListOf<String>()
        currencyCodes.add(noSelection)
        currencyCodes.addAll(currencies.map { it.currencyCode })
        return currencyCodes
    }
}