package com.forshant.pet.domain.entities

import com.squareup.moshi.Json

data class CityInfo(
    @field:Json(name = "temp")
    val cityTemperature: String
)
