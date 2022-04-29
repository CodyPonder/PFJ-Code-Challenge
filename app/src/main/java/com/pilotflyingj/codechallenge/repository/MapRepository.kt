package com.pilotflyingj.codechallenge.repository

import com.pilotflyingj.codechallenge.network.RetrofitServiceProvider
import com.pilotflyingj.codechallenge.network.models.ApiSite
import com.pilotflyingj.codechallenge.network.util.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MapRepository @Inject constructor(
    private val retrofitServiceProvider: RetrofitServiceProvider
) {
    // TODO: pull the service in, run any business logic through here so that the view model is
    // simply requesting data at this point

    private val json = Json { ignoreUnknownKeys = true }

    // Gets the locations from a local json file. Will convert to an actual retrofit call once I understand
    // how to read a Google Drive file (aka just so I can test the other aspects of the app)
    @ExperimentalSerializationApi
    fun getLocations(jsonFileString: String): Flow<ApiResponse<List<ApiSite>>> = flow {
        try {
            emit(ApiResponse.Loading<List<ApiSite>>(true))
			// mocked locations from local json file
            val locations = json.decodeFromString<List<ApiSite>>(jsonFileString)
            emit(ApiResponse.Success(locations))
        } catch (e: HttpException) {
            // Error with the request
            emit(ApiResponse.Except<List<ApiSite>>(e))
        } catch (e: IOException) {
            // Error with connection
            emit(ApiResponse.Except<List<ApiSite>>(e))
        } catch (e: Exception) {
            // Any uncaught exception
            emit(ApiResponse.Except<List<ApiSite>>(e))
        }
    }
}