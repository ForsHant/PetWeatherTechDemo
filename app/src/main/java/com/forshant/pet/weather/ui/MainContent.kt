package com.forshant.pet.weather.ui

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.forshant.pet.domain.entities.CityInfo
import com.forshant.pet.weather.ui.theme.PetWeatherTheme
import com.forshant.pet.weather.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun MainContent(
    context: Context,
    viewModel: MainViewModel,
) {
    val city by viewModel.city.collectAsState()
    val composeScope = rememberCoroutineScope()

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            if (city.id == -1L){
                CircularProgressIndicator()
            }else{
                CityComposable(cityName = city.cityName, cityInfo = city.cityInfo
                ) {
                    composeScope.launch {
                        viewModel.getCurrentCityWeather(55.7758, 37.6173)
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun MainContent_Preview(){
    PetWeatherTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column{
                CityComposable(cityName = "Moscow", cityInfo = CityInfo("12")) {

                }
            }
        }
    }
}