package com.forshant.pet.data.repositoriesImpl

import android.util.Log
import com.forshant.pet.data.BuildConfig
import com.forshant.pet.data.api.CityApi
import com.forshant.pet.data.api.CityApiResponse
import com.forshant.pet.data.api.toCity
import com.forshant.pet.domain.entities.City
import com.forshant.pet.domain.entities.CityInfo
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import retrofit2.Response

class CityRemoteDataSourceImplTest {

    val cityApi = mockk<CityApi>()
    val service = CityRemoteDataSourceImpl(cityApi)

    @Before
    fun setUp(){
        coEvery {
            cityApi.getCity(55.7558, 37.6173, BuildConfig.API_KEY).body()
        } returns CityApiResponse(CityInfo("24.0"), "Moscow")
    }

    @Test
    fun `Default usage test`() = runBlocking{
            assertNotNull(cityApi.getCity(55.7558, 37.6173, BuildConfig.API_KEY))
    }

    @Test
    fun `Show insides`() = runBlocking{
        val result = cityApi.getCity(55.7558, 37.6173, BuildConfig.API_KEY).body()
        val city = result?.toCity()
        city?.let{
            println(it.cityName)
            println(it.cityInfo.cityTemperature)
        }
        assertNotNull(city)
    }
}