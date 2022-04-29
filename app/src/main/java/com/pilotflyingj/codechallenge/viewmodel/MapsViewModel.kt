package com.pilotflyingj.codechallenge.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pilotflyingj.codechallenge.network.util.ApiResponse
import com.pilotflyingj.codechallenge.repository.MapRepository
import com.pilotflyingj.codechallenge.repository.models.Site
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.ExperimentalSerializationApi

class MapsViewModel @ViewModelInject constructor(
    private val mapRepository: MapRepository
) : ViewModel() {

    private val _locations = MutableLiveData<List<Site>>()  // locations retrieved and stored for viewing
    private val _apiResponse = MutableLiveData<ApiResponse<List<Site>>>()    // status of api call for any UI changes
    private var didRetrieveLocations = false

    fun getRetrievedLocations(): LiveData<List<Site>> = _locations

    fun getApiResponse(): LiveData<ApiResponse<List<Site>>> = _apiResponse

    @ExperimentalSerializationApi
    fun getLocations() {
        if (!didRetrieveLocations) {
            didRetrieveLocations = true
            mapRepository.getLocations().onEach {
                _apiResponse.value = it
                when (it) {
                    is ApiResponse.Loading -> {
                        // update the UI to show the user that the locations are currently being retrieved
                    }
                    is ApiResponse.Success -> {
                        // locations have successfully been retrieved
                        _locations.value = it.data
                    }
                    is ApiResponse.Error -> {
                        // update the UI to show the user there was an error retrieving the locations
                        // due to getting error code do anything special if required based on the code
                    }
                    is ApiResponse.Except -> {
                        // update the UI to show the user there was an error retrieving the locations
                        // log the event and exception for correction
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}