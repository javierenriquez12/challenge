package com.bcp.challengebcp.presentation.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bcp.challengebcp.R
import com.bcp.challengebcp.presentation.exchange.ExchangeRateActivity
import com.bcp.challengebcp.util.getViewModel
import com.bcp.challengebcp.util.nextScreenInOut

class SplashActivity : AppCompatActivity() {

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initViewModel()
        initEvents()
        startSplash()
    }

    private fun initEvents() {
        splashViewModel.actionNextScreenUI.observe(this, Observer {
            when (it) {
                SplashResult.ActionNextScreenUI -> {
                    nextScreen()
                }
            }
        })
    }

    private fun nextScreen() {
        nextScreenInOut(ExchangeRateActivity::class.java)
    }

    private fun initViewModel() {
        splashViewModel = getViewModel {
            SplashViewModel()
        }
    }

    private fun startSplash() {
        splashViewModel.loadSplash()
    }
}