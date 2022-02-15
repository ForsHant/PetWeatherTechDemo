package com.forshant.pet.weather.di

import android.content.Context
import com.forshant.pet.data.repositoriesImpl.CityRepositoryImpl
import com.forshant.pet.domain.repositories.CityRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(impl: CityRepositoryImpl): CityRepository
}