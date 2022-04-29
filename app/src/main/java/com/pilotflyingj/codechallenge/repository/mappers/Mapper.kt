package com.pilotflyingj.codechallenge.repository.mappers

import com.pilotflyingj.codechallenge.network.models.ApiSite
import com.pilotflyingj.codechallenge.repository.models.Site

interface Mapper {
    fun toSite(apiSite: ApiSite): Site
}