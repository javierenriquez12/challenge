package com.bcp.challengebcp

import android.app.Application
import android.util.Log
import com.bcp.challengebcp.model.InputExchangeModel
import com.google.gson.Gson

class Application: Application() {

    override fun onCreate() {
        super.onCreate()
        val data = getDataJson()
        val inputExchangeModel = Gson().fromJson(data,InputExchangeModel::class.java)
        Log.d("preuba",inputExchangeModel.amountMoneyChange)
    }

    private fun getDataJson() = applicationContext.resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }

}