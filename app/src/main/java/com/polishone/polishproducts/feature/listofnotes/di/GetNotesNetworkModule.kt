package com.polishone.polishproducts.feature.listofnotes.di

import com.polishone.polishproducts.feature.listofnotes.data.network.api.GetTasksApi
import com.polishone.polishproducts.feature.listofnotes.data.network.repository.GetTasksRepositoryImpl
import com.polishone.polishproducts.feature.listofnotes.domain.repository.GetTasksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetNotesNetworkModule {

    @Provides
    @Singleton
    fun provideGetTaskApi(retrofit: Retrofit): GetTasksApi {
        return retrofit.create(GetTasksApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGetTaskRepository(getTasksApi: GetTasksApi): GetTasksRepository {
        return GetTasksRepositoryImpl(getTasksApi)
    }
}