package com.forshant.pet.data.di

import com.forshant.pet.data.api.CityApi
import com.forshant.pet.data.repositories.CityRemoteDataSource
import com.forshant.pet.data.repositoriesImpl.CityRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideUrl(): String = "http://api.openweathermap.org/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String = "http://api.openweathermap.org"): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .build()

    @Provides
    @Singleton
    fun provideCityService(retrofit: Retrofit): CityApi = retrofit.create(CityApi::class.java)

    @Provides
    @Singleton
    fun provideCityRemoteDataSource(cityApi: CityApi): CityRemoteDataSource = CityRemoteDataSourceImpl(cityApi)
}