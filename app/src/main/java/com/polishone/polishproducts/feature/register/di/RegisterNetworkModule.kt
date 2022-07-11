package com.polishone.polishproducts.feature.register.di

import com.polishone.polishproducts.feature.register.data.network.api.RegisterApi
import com.polishone.polishproducts.feature.register.data.repository.RegisterRepositoryImpl
import com.polishone.polishproducts.feature.register.domain.repository.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegisterNetworkModule {

    @Provides
    @Singleton
    fun provideRegisterApi(retrofit: Retrofit): RegisterApi {
        return retrofit.create(RegisterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(registerApi: RegisterApi): RegisterRepository {
        return RegisterRepositoryImpl(registerApi)
    }
}
