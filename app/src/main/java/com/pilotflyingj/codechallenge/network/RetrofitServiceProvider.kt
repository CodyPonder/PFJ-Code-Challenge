package com.pilotflyingj.codechallenge.network

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitServiceProvider @Inject constructor(
	private val okHttpProvider: OkHttpProvider
) {
	val locationServiceProvider by lazy {
		Retrofit.Builder()
			.baseUrl("https://drive.google.com")
			.client(okHttpProvider.okHttpClient)
			.build()
			.create(LocationService::class.java)
    }
}