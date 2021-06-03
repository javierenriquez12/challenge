package com.bcp.challengebcp.presentation.exchange

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bcp.challengebcp.R
import com.bcp.challengebcp.util.getViewModel

class ExchangeRateActivity : AppCompatActivity() {

    private lateinit var exchangeRateViewModel: ExchangeRateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange)
        exchangeRateViewModel = getViewModel { ExchangeRateViewModel() }
        exchangeRateViewModel.testBinding()
    }
}