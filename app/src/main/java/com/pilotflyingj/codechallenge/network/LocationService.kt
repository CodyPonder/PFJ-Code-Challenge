package com.pilotflyingj.codechallenge.network

import com.pilotflyingj.codechallenge.network.models.ApiSite
import retrofit2.Response
import retrofit2.http.GET

interface LocationService {
	@GET("PFJCodeChallenge/pfj-locations/master/locations.json")
	suspend fun getLocations(): Response<List<ApiSite>>   // will convert to Any once I figure out how to read from the file
}