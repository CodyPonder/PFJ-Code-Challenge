package com.pilotflyingj.codechallenge

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pilotflyingj.codechallenge.viewmodel.MapsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val mapsViewModel by viewModels<MapsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        (supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment)?.let {
            it.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        getLocations(googleMap)
        googleMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(LatLng(39.828175, -98.5795), 3.5f)
        )  // Center of United States
    }

    /**
     * Calls the ViewModel to get locations of stations and adds their markers to the provided GoogleMap
     */
    private fun getLocations(googleMap: GoogleMap) {
        // observe the retrieved locations
        mapsViewModel.getRetrievedLocations().observe(this) {
            Log.d("MapsActivity", "it: $it")
            mapsViewModel.getRetrievedLocations().removeObservers(this)
            it.forEach { site ->
                googleMap.addMarker(
                    MarkerOptions()
                        .position(site.location)
                        .title(site.name)
                )
            }
        }

        mapsViewModel.getLocations()
    }
}