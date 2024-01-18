package com.example.wizard.di

import com.example.wizard.repository.ContactRepository
import com.example.wizard.repository.ContactRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideContactRepository(repository: ContactRepositoryImpl):
            ContactRepository = repository


}
