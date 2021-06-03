package com.bcp.challengebcp.data

import android.content.Context
import android.content.res.Resources
import com.bcp.challengebcp.R
import com.bcp.challengebcp.model.InputExchangeModel
import com.bcp.challengebcp.model.ListTypeMoney
import com.google.gson.Gson

class DataChallenge {

    private var resources: Resources

    companion object {
        lateinit var context: Context
    }

    init {
        resources = context.resources
    }

    fun getDataExchangeRate(): String {
        return resources.openRawResource(R.raw.data).bufferedReader()
            .use { it.readText() }
    }

    fun getExchangeRate(): InputExchangeModel =
        Gson().fromJson(StorageChallenge().getExchangeRate(), InputExchangeModel::class.java)

    fun getTypeMoneyList(): ListTypeMoney =
        Gson().fromJson(getDataTypeMoneyList(), ListTypeMoney::class.java)

    fun getDataTypeMoneyList(): String {
        return resources.openRawResource(R.raw.type_money_list).bufferedReader()
            .use { it.readText() }
    }
}