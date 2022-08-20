package com.polishone.polishproducts.feature.listofnotes

import com.polishone.polishproducts.feature.listofnotes.data.network.GetTasksApi
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
}