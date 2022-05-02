package com.pilotflyingj.codechallenge.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiSite(
    @SerialName("StoreName")
    val name: String,
    @SerialName("Address")
    val address: String,
    @SerialName("Latitude")
    val latitude: Double,
    @SerialName("Longitude")
    val longitude: Double
)