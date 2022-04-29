package com.pilotflyingj.codechallenge

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pilotflyingj.codechallenge.network.util.ApiResponse
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
        // TODO: center camera on the entire USA
        // TODO: subscribe to live data for view model so that markers get added
        // TODO: make sure rotation works
        getLocations(googleMap)
	    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(39.828175, -98.5795), 3.5f))  // Center of United States
    }

	/**
	 * Calls the ViewModel to get locations of stations and adds their markers to the provided GoogleMap
	 */
    private fun getLocations(googleMap: GoogleMap) {
        //todo: Combine the observers to remove them on either api fail or retrieve update

        // observe the retrieved locations
        mapsViewModel.getRetrievedLocations().observe(this) {
            it.forEach { site ->
                googleMap.addMarker(
                    MarkerOptions()
                        .position(site.location)
                        .title(site.name)
                )
            }
        }

        // observe the api response
        mapsViewModel.getApiResponse().observe(this) {
			when (it) {
				is ApiResponse.Loading -> {
					Toast.makeText(this, "Loading Locations...", Toast.LENGTH_SHORT).show()
				}
				is ApiResponse.Error, is ApiResponse.Except -> {
					Toast.makeText(this,"Failed to retrieve locations", Toast.LENGTH_SHORT).show()
				}
				else -> {}
			}
        }

		mapsViewModel.getLocations()
    }
}