package com.forshant.pet.data.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CityApi {

    companion object{
        fun create(baseUrl: String): CityApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(CityApi::class.java)
        }
    }

    @GET("data/2.5/weather")
    suspend fun getCity(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("appid") apiKey: String, @Query("units") units: String = "metric"): Response<CityApiResponse>
}