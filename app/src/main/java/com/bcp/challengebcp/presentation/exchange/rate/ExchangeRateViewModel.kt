package com.bcp.challengebcp.presentation.exchange.rate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcp.challengebcp.model.InputExchangeModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExchangeRateViewModel : ViewModel() {

    var testBindingLiveData = MutableLiveData<InputExchangeModel>()

    fun testBinding(){
        viewModelScope.launch {
            testBindingLiveData.value = InputExchangeModel("100","soles","370","dólares")
            this@ExchangeRateViewModel.testBindingLiveData = testBindingLiveData
        }
    }
}