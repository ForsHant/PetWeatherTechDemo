package com.forshant.pet.data.di

import android.content.Context
import com.forshant.pet.data.api.CityApi
import com.forshant.pet.data.repositories.CityRemoteDataSource
import com.forshant.pet.data.repositoriesImpl.CityRemoteDataSourceImpl
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideUrl(): String = "https://api.openweathermap.org/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String = "http://api.openweathermap.org"): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideCityService(retrofit: Retrofit): CityApi = retrofit.create(CityApi::class.java)

    @Provides
    @Singleton
    fun provideCityRemoteDataSource(cityApi: CityApi): CityRemoteDataSource = CityRemoteDataSourceImpl(cityApi)
}