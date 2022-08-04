package com.polishone.polishproducts.feature.createnote.di

import com.polishone.polishproducts.feature.createnote.data.network.api.CreateNoteApi
import com.polishone.polishproducts.feature.createnote.data.repostory.CreateNoteRepositoryImpl
import com.polishone.polishproducts.feature.createnote.domain.repository.CreateNoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CreateNoteNetworkModule {

    @Provides
    @Singleton
    fun provideCreateNoteApi(retrofit: Retrofit): CreateNoteApi {
        return retrofit.create(CreateNoteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCreateNoteRepository(createNoteApi: CreateNoteApi): CreateNoteRepository {
        return CreateNoteRepositoryImpl(createNoteApi)
    }
}
