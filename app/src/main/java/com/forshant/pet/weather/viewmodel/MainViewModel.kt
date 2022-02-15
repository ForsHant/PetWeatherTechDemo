package com.forshant.pet.weather.viewmodel

import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forshant.pet.domain.entities.City
import com.forshant.pet.domain.entities.CityInfo
import com.forshant.pet.domain.usecases.GetCityUseCase
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCityUseCase: GetCityUseCase,
): ViewModel() {
    private var locationManager: FusedLocationProviderClient? = null

    val city = MutableStateFlow(City(-1, CityInfo("N/A"), "N/A"))

    suspend fun getCurrentCityWeather(lat: Double, lon: Double){
        val result = getCityUseCase.invoke(lat, lon)
        if(result.isSuccess){
            city.value = result.getOrDefault(city.value)
        }else{
            Log.d("Debug", "Error retrieving city ${result.exceptionOrNull()}")
        }
    }

    suspend fun getCurrentCityWeather(context: Context){
        getCurrentLocation(context)
    }

    private suspend fun getCurrentLocation(context: Context){
        if(locationManager == null){
            locationManager = LocationServices.getFusedLocationProviderClient(context)
        }

        try {
            locationManager!!.getCurrentLocation(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY, CancellationTokenSource().token).addOnCompleteListener {
                if(it.isSuccessful){
                    viewModelScope.launch {
                        getCurrentCityWeather(it.result.latitude, it.result.longitude)
                    }
                }
            }
        }catch (e: SecurityException){
            Log.d("Debug", "Fetching location failed")
        }
    }
}