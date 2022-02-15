package com.forshant.pet.weather.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.forshant.pet.domain.entities.City
import com.forshant.pet.domain.entities.CityInfo
import com.forshant.pet.domain.usecases.GetCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCityUseCase: GetCityUseCase
): ViewModel() {
    var city = MutableStateFlow(City(-1, CityInfo("N/A"), "N/A"))

    suspend fun getCurrentCityWeather(lat: Double, lon: Double){
        val result = getCityUseCase.invoke(lat, lon)
        if(result.isSuccess){
            city.value = result.getOrDefault(city.value)
        }else{
            Log.d("Debug", "Error retrieving city ${result.exceptionOrNull()}")
        }
    }
}