package com.pilotflyingj.codechallenge.network

import com.pilotflyingj.codechallenge.network.models.ApiSite
import retrofit2.Response
import retrofit2.http.GET

interface LocationService {
    // TODO: define the API endpoint here, use the models in network layer with Kotlin Serialization
    // Use https://drive.google.com/file/d/14IG3sYCHRAU5WyKcfgTUw9LS1Oo0DBF4/view?usp=sharing

	@GET("PFJCodeChallenge/pfj-locations/master/locations.json")
	suspend fun getLocations(): Response<List<ApiSite>>   // will convert to Any once I figure out how to read from the file
}