package com.bcp.challengebcp.data

import android.content.Context
import android.content.SharedPreferences

class StorageChallenge {
    private val PREFS_NAME = "SharedPreferenceChallengeBcp"
    private var preferences: SharedPreferences

    companion object {
        lateinit var context: Context
        private const val EXCHANGE_RATE_DATA = "EXCHANGERATEDATA"
        private const val FLAG_FIRST_WELCOME = "FLAGFIRSTWELCOME"
    }

    init {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setFirstWelcome(flag: Boolean) {
        preferences.edit().putBoolean(FLAG_FIRST_WELCOME, false).apply()
    }

    fun isFirstWelcome(): Boolean {
        return preferences.getBoolean(FLAG_FIRST_WELCOME, true)
    }

    fun setExchangeRate(exchangeRate: String) {
        preferences.edit().putString(EXCHANGE_RATE_DATA, exchangeRate).apply()
    }

    fun getExchangeRate(): String {
        return preferences.getString(EXCHANGE_RATE_DATA, "") ?: ""
    }
}