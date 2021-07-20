package com.gauravsingh.currencyconverter.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.viewModels
import com.gauravsingh.currencyconverter.R
import com.gauravsingh.currencyconverter.databinding.ActivityDashboardBinding
import com.gauravsingh.currencyconverter.ui.BaseActivity
import com.gauravsingh.currencyconverter.utils.MarginItemDecoration
import com.gauravsingh.currencyconverter.utils.isNetworkConnected
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    private val viewModel: DashboardViewModel by viewModels()

    private val binding: ActivityDashboardBinding by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }

    private val currencyAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter<String>(this, R.layout.item_spinner, R.id.tv_selected_currency)
    }

    private val currencyDetailsAdapter: CurrencyDetailsAdapter by lazy { CurrencyDetailsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        bindData()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        (view as? TextView)?.setTextColor(Color.BLACK)
        binding.btnConvert.isEnabled = position != 0
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        binding.btnConvert.isEnabled = false
    }

    private fun initView() {
        setSupportActionBar(binding.toolbar)
        binding.spinnerSourceCurrency.adapter = currencyAdapter
        binding.rvCurrenciesQuotes.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.dp_16)))
        binding.rvCurrenciesQuotes.adapter = currencyDetailsAdapter

        binding.spinnerSourceCurrency.onItemSelectedListener = this
        binding.btnConvert.setOnClickListener { convert() }
    }

    private fun bindData() {
        if (this.isNetworkConnected()) {
            binding.pbDashboard.visibility = View.VISIBLE
            viewModel.fetchCurrencies()
        } else {
            binding.pbDashboard.visibility = View.GONE
            showSnackbar(getString(R.string.no_internet))
        }
        viewModel.currencyCodes.observe(this, ::bindCurrencyCodes)
        viewModel.currencyDetails.observe(this, ::bindCurrencyDetails)
        viewModel.event.observe(this, ::handleEvent)
    }

    private fun bindCurrencyCodes(currencies: List<String>) {
        currencyAdapter.clear()
        currencyAdapter.addAll(currencies)
        binding.pbDashboard.visibility = View.GONE
    }

    private fun bindCurrencyDetails(currencyDetails: List<CurrencyDetails>) {
        currencyDetailsAdapter.currencyDetails = currencyDetails
        currencyDetailsAdapter.notifyDataSetChanged()
        binding.pbDashboard.visibility = View.GONE
    }

    private fun handleEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.InProgress -> {
                binding.pbDashboard.visibility = View.VISIBLE
            }
            is DashboardEvent.Error -> {
                binding.pbDashboard.visibility = View.GONE
                showSnackbar(event.message)
            }
        }
    }

    private fun convert() {

        if (this.isNetworkConnected()) {
            binding.btnConvert.isEnabled = false
            viewModel.fetchCurrencyQuotes(
                binding.spinnerSourceCurrency.selectedItem.toString(),
                binding.tietSource.text?.toString()
            )
        } else {
            binding.pbDashboard.visibility = View.GONE
            showSnackbar(getString(R.string.no_internet))
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}