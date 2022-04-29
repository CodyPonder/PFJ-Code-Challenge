package com.pilotflyingj.codechallenge.network

import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OkHttpProvider @Inject constructor(

) {
	val okHttpClient = OkHttpClient.Builder().build()
}