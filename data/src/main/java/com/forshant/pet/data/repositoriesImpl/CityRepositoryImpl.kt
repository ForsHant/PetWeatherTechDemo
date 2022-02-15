package com.forshant.pet.data.repositoriesImpl

import com.forshant.pet.data.repositories.CityRemoteDataSource
import com.forshant.pet.domain.entities.City
import com.forshant.pet.domain.repositories.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val remoteDataSource: CityRemoteDataSource
    ) : CityRepository {

    override suspend fun getRemoteCity(lat: Double, lon: Double): Result<City> {
        return remoteDataSource.getCity(lat, lon)
    }

}