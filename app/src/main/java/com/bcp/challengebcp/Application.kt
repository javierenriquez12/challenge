package com.bcp.challengebcp

import android.app.Application
import com.bcp.challengebcp.data.DataChallenge
import com.bcp.challengebcp.data.StorageChallenge

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        StorageChallenge.context = applicationContext
        DataChallenge.context = applicationContext
        if (StorageChallenge().isFirstWelcome()) {
            StorageChallenge().setFirstWelcome(false)
            StorageChallenge().setExchangeRate(DataChallenge().getDataExchangeRate())
        }
    }
}