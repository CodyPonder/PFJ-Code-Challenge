package com.pilotflyingj.codechallenge.repository.mappers

import com.google.android.gms.maps.model.LatLng
import com.pilotflyingj.codechallenge.network.models.ApiSite
import com.pilotflyingj.codechallenge.repository.models.Site

object MapperImpl: Mapper {
    override fun toSite(apiSite: ApiSite): Site {
        return Site(apiSite.name, LatLng(apiSite.latitude, apiSite.longitude))
    }
}