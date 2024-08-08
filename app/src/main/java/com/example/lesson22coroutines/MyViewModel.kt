package com.example.lesson22coroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext

class MyViewModel : ViewModel() {
	private val _uiState = MutableLiveData<UIState>(UIState.Empty)
	val uiState: LiveData<UIState> = _uiState
	private val repo = MyApplication.getApp().repo
	fun getData() {
		_uiState.value = UIState.Processing
		viewModelScope.launch {
			withContext(Dispatchers.IO){
				try {
					val bitcoin = repo.getCurrencyByName("bitcoin")
					if (bitcoin.isSuccessful) {
						val data = bitcoin.body()?.data
						_uiState.postValue(UIState.Result("${data?.id} ${data?.rateUsd}"))
					} else _uiState.postValue(UIState.Error("Error Code ${bitcoin.code()}"))
				} catch (e: Exception) {
					_uiState.postValue(UIState.Error(e.localizedMessage))
				}
			}
		}
	}

	sealed class UIState {
		object Empty : UIState()
		object Processing : UIState()
		class Result(val title: String) : UIState()
		class Error(val description: String) : UIState()
	}
}
