package com.pilotflyingj.codechallenge.repository

import com.pilotflyingj.codechallenge.network.OkHttpProvider
import com.pilotflyingj.codechallenge.network.RetrofitServiceProvider
import com.util.getLocationsResponse
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
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
        assertTrue("First emission is the locations", flow[0].isNotEmpty())
    }
}