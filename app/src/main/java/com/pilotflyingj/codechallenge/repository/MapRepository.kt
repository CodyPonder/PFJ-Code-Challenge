package com.pilotflyingj.codechallenge.repository

import com.pilotflyingj.codechallenge.network.RetrofitServiceProvider
import com.pilotflyingj.codechallenge.network.util.ApiResponse
import com.pilotflyingj.codechallenge.repository.mappers.MapperImpl
import com.pilotflyingj.codechallenge.repository.models.Site
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MapRepository @Inject constructor(
	private val retrofitServiceProvider: RetrofitServiceProvider
) {
	// TODO: pull the service in, run any business logic through here so that the view model is
	// simply requesting data at this point

	@ExperimentalSerializationApi
	fun getLocations(): Flow<ApiResponse<List<Site>>> = flow {
		try {
			emit(ApiResponse.Loading<List<Site>>(true))

			val response = retrofitServiceProvider.locationServiceProvider.getLocations()
			if (response.isSuccessful && response.body() != null) {
				// successful response and the body is not null (thus locations have been retrieved)
				val apiLocations = response.body()
				val locations = apiLocations!!.map { MapperImpl.toSite(it) }
				emit(ApiResponse.Success<List<Site>>(locations))
			} else {
				// success went through but did not receive a passing response code
				emit(ApiResponse.Error<List<Site>>(response.code()))
			}
		} catch (e: HttpException) {
			// Error with the request
			emit(ApiResponse.Except<List<Site>>(e))
		} catch (e: IOException) {
			// Error with connection
			emit(ApiResponse.Except<List<Site>>(e))
		} catch (e: Exception) {
			// Any uncaught exception
			emit(ApiResponse.Except<List<Site>>(e))
		}
	}
}