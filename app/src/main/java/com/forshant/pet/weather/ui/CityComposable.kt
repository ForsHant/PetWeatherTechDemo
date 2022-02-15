package com.forshant.pet.weather.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.forshant.pet.domain.entities.CityInfo

@Composable
fun CityComposable(cityName: String, cityInfo: CityInfo, onClickFunc: () -> Unit) {
    Column(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row{
            Text(text = cityName, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
        Row{
            Text(text = cityInfo.cityTemperature + " °C", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
        Row(Modifier.padding(top = 20.dp)){
            Button(
                onClick = onClickFunc,
                modifier = Modifier.width(320.dp)
            ) {
                Text("Get my city")
            }
        }
    }
}

@Composable
@Preview
fun CityPreview(){
    CityComposable(cityName = "Moscow", cityInfo = CityInfo("-1"), {})
}