package com.pilotflyingj.codechallenge.network

import retrofit2.Response
import retrofit2.http.GET

interface LocationService {
    // TODO: define the API endpoint here, use the models in network layer with Kotlin Serialization
    // Use https://drive.google.com/file/d/14IG3sYCHRAU5WyKcfgTUw9LS1Oo0DBF4/view?usp=sharing

	@GET("file/d/14IG3sYCHRAU5WyKcfgTUw9LS1Oo0DBF4/view?usp=sharing")
	suspend fun getLocations(): Response<Any>   // will convert to Any once I figure out how to read from the file
}