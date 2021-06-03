package com.bcp.challengebcp.presentation.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcp.challengebcp.util.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    val actionNextScreenUI = MutableLiveData<SplashResult>()

    fun loadSplash(){
        viewModelScope.launch {
            delay(Constants.TIME_SPLASH)
            actionNextScreenUI.value = SplashResult.ActionNextScreenUI
        }
    }
}