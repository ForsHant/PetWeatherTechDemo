package com.forshant.pet.domain.usecases

import com.forshant.pet.domain.repositories.CityRepository
import javax.inject.Inject

class GetCityUseCase @Inject constructor(
    private val cityRepository: CityRepository
    ) {
    suspend operator fun invoke(lat: Double, lon: Double) = cityRepository.getRemoteCity(lat, lon)
}