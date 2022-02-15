package com.forshant.pet.domain.repositories

import com.forshant.pet.domain.entities.City
import com.forshant.pet.domain.entities.CityInfo

interface CityRepository {

    suspend fun getRemoteCity(lat: Double, lon: Double): Result<City>

}