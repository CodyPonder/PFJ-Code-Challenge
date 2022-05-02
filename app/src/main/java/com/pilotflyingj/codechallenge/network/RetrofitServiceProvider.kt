package com.pilotflyingj.codechallenge.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitServiceProvider @Inject constructor(
	private val okHttpProvider: OkHttpProvider
) {
	private val contentType = MediaType.get("application/json")
	private val json = Json { ignoreUnknownKeys = true }

	@ExperimentalSerializationApi
	val locationServiceProvider: LocationService by lazy {
		Retrofit.Builder()
			.baseUrl("https://raw.githubusercontent.com")
			.addConverterFactory(json.asConverterFactory(contentType))
			.client(okHttpProvider.okHttpClient)
			.build()
			.create(LocationService::class.java)
    }
}