package com.gauravsingh.currencyconverter.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gauravsingh.currencyconverter.databinding.ItemCurrencyQuoteBinding

class CurrencyDetailsAdapter : RecyclerView.Adapter<CurrencyDetailsViewHolder>() {

    var currencyDetails: List<CurrencyDetails>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyDetailsViewHolder {

        val binding = ItemCurrencyQuoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CurrencyDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyDetailsViewHolder, position: Int) {
        currencyDetails?.let { holder.bindData(it[position]) }
    }

    override fun getItemCount(): Int {
        return currencyDetails?.size ?: 0
    }
}