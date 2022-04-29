package com.pilotflyingj.codechallenge.repository

import com.pilotflyingj.codechallenge.network.OkHttpProvider
import com.pilotflyingj.codechallenge.network.RetrofitServiceProvider
import com.pilotflyingj.codechallenge.network.util.ApiResponse
import com.util.getLocationsResponse
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

@ExperimentalSerializationApi
class MapRepositoryTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl

    // system to test
    private lateinit var mapRepository: MapRepository

    // dependencies
    private lateinit var retrofitServiceProvider: RetrofitServiceProvider
    private lateinit var okHttpProvider: OkHttpProvider

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("/")

        okHttpProvider = OkHttpProvider()
        retrofitServiceProvider = RetrofitServiceProvider(okHttpProvider)
        mapRepository = MapRepository(retrofitServiceProvider)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getLocationsTest() = runBlocking {
        //condition the response
        mockWebServer.enqueue(MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(getLocationsResponse))

        val flow = mapRepository.getLocations().toList()
        assertTrue("First emission is loading", flow[0] is ApiResponse.Loading)
        assertTrue("Second emission is the data", flow[1] is ApiResponse.Success)

        val data = (flow[1] as ApiResponse.Success).data
        assertNotNull("The data is not null", data)
        assertTrue("The data is not empty", data.isNotEmpty())
    }
}