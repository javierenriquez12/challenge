package com.bcp.challengebcp.presentation.exchange.rate.typemoneylist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcp.challengebcp.data.DataChallenge
import com.bcp.challengebcp.data.StorageChallenge
import com.bcp.challengebcp.model.InputExchangeModel
import com.bcp.challengebcp.model.ListTypeMoney
import com.google.gson.Gson
import kotlinx.coroutines.launch

class TypeMoneyListViewModel : ViewModel() {

    var typeMoneyListeLiveData = MutableLiveData<ListTypeMoney>()
    fun getTypeMoneyList() {
        viewModelScope.launch {
            typeMoneyListeLiveData.value = getListTypeMoney()
            this@TypeMoneyListViewModel.typeMoneyListeLiveData = typeMoneyListeLiveData
        }
    }

    private fun getListTypeMoney(): ListTypeMoney =
        DataChallenge().getTypeMoneyList()

    fun saveNewTypeMoney(inputExchangeModel: InputExchangeModel) {
        StorageChallenge().setExchangeRate(Gson().toJson(inputExchangeModel))
    }
}