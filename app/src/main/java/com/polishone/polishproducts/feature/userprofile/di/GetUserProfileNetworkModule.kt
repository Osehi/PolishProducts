package com.polishone.polishproducts.feature.userprofile.di

import com.polishone.polishproducts.feature.userprofile.data.network.api.GetUserProfileApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetUserProfileNetworkModule {

    @Provides
    @Singleton
    fun provideGetUserProfileApi(retrofit: Retrofit): GetUserProfileApi {
        return retrofit.create(GetUserProfileApi::class.java)
    }
}
