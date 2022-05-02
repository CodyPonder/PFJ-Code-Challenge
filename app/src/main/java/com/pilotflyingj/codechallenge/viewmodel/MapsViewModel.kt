package com.pilotflyingj.codechallenge.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pilotflyingj.codechallenge.repository.MapRepository
import com.pilotflyingj.codechallenge.repository.models.Site
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.ExperimentalSerializationApi

class MapsViewModel @ViewModelInject constructor(
    private val mapRepository: MapRepository
) : ViewModel() {

    private val _locations = MutableLiveData<List<Site>>()
    private var didRetrieveLocations = false

    fun getRetrievedLocations(): LiveData<List<Site>> = _locations

    @ExperimentalSerializationApi
    fun getLocations() {
        if (!didRetrieveLocations) {
            didRetrieveLocations = true
            mapRepository.getLocations().onEach {
                _locations.value = it
            }.launchIn(viewModelScope)
        }
    }
}