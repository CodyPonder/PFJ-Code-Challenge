package com.pilotflyingj.codechallenge.repository

import com.pilotflyingj.codechallenge.network.RetrofitServiceProvider
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

	@ExperimentalSerializationApi
	fun getLocations(): Flow<List<Site>> = flow {
		try {
			val response = retrofitServiceProvider.locationServiceProvider.getLocations()
			if (response.isSuccessful && response.body() != null) {
				// successful response and the body is not null (thus locations have been retrieved)
				val apiLocations = response.body()
				val locations = apiLocations!!.map { MapperImpl.toSite(it) }
				emit(locations)
			} else {
				// success went through but did not receive a passing response code
				emit(listOf<Site>())
			}
		} catch (e: HttpException) {
			// Error with the request
			emit(listOf<Site>())
		} catch (e: IOException) {
			// Error with connection
			emit(listOf<Site>())
		} catch (e: Exception) {
			// Any uncaught exception
			emit(listOf<Site>())
		}
	}
}