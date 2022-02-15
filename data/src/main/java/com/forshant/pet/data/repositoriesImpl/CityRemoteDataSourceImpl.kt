package com.forshant.pet.data.repositoriesImpl

import com.forshant.pet.data.BuildConfig
import com.forshant.pet.data.api.CityApi
import com.forshant.pet.data.api.toCity
import com.forshant.pet.data.repositories.CityRemoteDataSource
import com.forshant.pet.domain.entities.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class CityRemoteDataSourceImpl @Inject constructor(
    private val service: CityApi
) : CityRemoteDataSource {
    override suspend fun getCity(lat: Double, lon: Double): Result<City> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getCity(lat, lon, BuildConfig.API_KEY)
                if (response.isSuccessful) {
                    return@withContext Result.success(response.body()!!.toCity())
                } else {
                    return@withContext Result.failure(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.failure(e)
            }
        }
}