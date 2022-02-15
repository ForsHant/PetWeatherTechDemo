package com.forshant.pet.weather.di

import com.forshant.pet.data.repositoriesImpl.CityRepositoryImpl
import com.forshant.pet.domain.repositories.CityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(impl: CityRepositoryImpl): CityRepository
}