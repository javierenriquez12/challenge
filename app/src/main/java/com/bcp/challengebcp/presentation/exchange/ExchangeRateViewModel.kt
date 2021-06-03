package com.bcp.challengebcp.presentation.exchange

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcp.challengebcp.data.DataChallenge
import com.bcp.challengebcp.model.InputExchangeModel
import kotlinx.coroutines.launch

class ExchangeRateViewModel : ViewModel() {

    var inputExchangeRateLiveData = MutableLiveData<InputExchangeModel>()

    fun testBinding() {
        viewModelScope.launch {
            inputExchangeRateLiveData.value =
                obtainExchangeModel()
            this@ExchangeRateViewModel.inputExchangeRateLiveData = inputExchangeRateLiveData
        }
    }

    private fun obtainExchangeModel(): InputExchangeModel {
        return DataChallenge().getExchangeRate()
    }
}