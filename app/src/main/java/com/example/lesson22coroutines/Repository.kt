package com.example.lesson22coroutines

import retrofit2.Response
import retrofit2.Retrofit

class Repository(private val apiClient: Retrofit) {
	suspend fun getCurrencyByName(name:String): Response<BitcoinResponse> {
		val apiInterface = apiClient.create(ApiInterface::class.java)
		return apiInterface.getCryptoByName(name)
	}

}