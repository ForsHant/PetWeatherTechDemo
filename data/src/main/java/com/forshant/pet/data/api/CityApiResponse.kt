package com.forshant.pet.data.api

import com.forshant.pet.domain.entities.City
import com.forshant.pet.domain.entities.CityInfo
import com.squareup.moshi.Json

data class CityApiResponse(
    @field:Json(name = "main")
    val cityInfo: CityInfo,
    @field:Json(name = "name")
    val cityName: String
)
fun CityApiResponse.toCity(): City {
    return City(0, this.cityInfo, this.cityName)
}
