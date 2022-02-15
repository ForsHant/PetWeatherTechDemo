package com.forshant.pet.weather.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.forshant.pet.domain.entities.CityInfo
import com.forshant.pet.weather.ui.theme.PetWeatherTheme
import com.forshant.pet.weather.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainContent(
    context: Context,
    viewModel: MainViewModel,
) {
    val city by viewModel.city.collectAsState()
    val composeScope = rememberCoroutineScope()

    val launchPermissions = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ){
        if(it) {
            getLocation(context, composeScope, viewModel, null)
        }
    }

    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(top = 20.dp)){
        Text(text = "Weather tech demo")
    }

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            if (city.id == -1L){
                CircularProgressIndicator()
            }else{
                CityComposable(cityName = city.cityName, cityInfo = city.cityInfo
                ) {
                    getLocation(context, composeScope, viewModel, launchPermissions)
                }
            }
        }
    }
}

fun getLocation(context: Context, composeScope: CoroutineScope, viewModel: MainViewModel, launchPermissions: ActivityResultLauncher<String>?){
    when(ContextCompat.checkSelfPermission(context, Context.LOCATION_SERVICE)){
        PackageManager.PERMISSION_GRANTED -> {
            composeScope.launch {
                viewModel.getCurrentCityWeather(context)
            }
        }
        PackageManager.PERMISSION_DENIED -> {
            launchPermissions?.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
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