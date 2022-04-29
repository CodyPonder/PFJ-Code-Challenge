package com.pilotflyingj.codechallenge.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitServiceProvider @Inject constructor(
	private val okHttpProvider: OkHttpProvider
) {
	val locationServiceProvider by lazy {
		Retrofit.Builder()
			.baseUrl("https://raw.githubusercontent.com")
			.addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
			.client(okHttpProvider.okHttpClient)
			.build()
			.create(LocationService::class.java)
    }
}