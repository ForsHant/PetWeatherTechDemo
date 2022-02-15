package com.forshant.pet.weather.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.forshant.pet.domain.entities.CityInfo

@Composable
fun CityComposable(cityName: String, cityInfo: CityInfo, onClickFunc: () -> Unit) {
    Column(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center)
    ) {
        Row{
            Text(text = cityName, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        }
        Row{
            Text(text = cityInfo.cityTemperature, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        }
        Row(horizontalArrangement = Arrangement.Center){
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