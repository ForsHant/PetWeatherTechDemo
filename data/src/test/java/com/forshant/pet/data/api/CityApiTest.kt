package com.forshant.pet.data.api

import com.forshant.pet.data.BuildConfig
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class CityApiTest {

    @Test
    fun `Default use case`() = runBlocking{
        val api = CityApi.create("http://api.openweathermap.org/")
        val response = api.getCity(55.7558, 37.6173, BuildConfig.API_KEY)
        assert(response.isSuccessful)
        assertNotNull(response.body())
        println(response.body()!!.cityInfo)
        println(response.body()!!.cityName)
    }

}