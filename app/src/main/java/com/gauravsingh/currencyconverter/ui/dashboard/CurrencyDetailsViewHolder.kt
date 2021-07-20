package com.gauravsingh.currencyconverter.ui.dashboard

import androidx.recyclerview.widget.RecyclerView
import com.gauravsingh.currencyconverter.databinding.ItemCurrencyQuoteBinding

class CurrencyDetailsViewHolder(private val binding: ItemCurrencyQuoteBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(currencyDetails: CurrencyDetails) {
        binding.currencyDetails = currencyDetails
        binding.executePendingBindings()
    }
}