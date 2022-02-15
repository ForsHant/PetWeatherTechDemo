package com.forshant.pet.data.repositories

import com.forshant.pet.domain.entities.City

interface CityRemoteDataSource {
    suspend fun getCity(lat: Double, lon: Double): Result<City>
}