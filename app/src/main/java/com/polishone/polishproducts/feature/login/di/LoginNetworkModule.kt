package com.polishone.polishproducts.feature.login.di

import com.polishone.polishproducts.feature.login.data.network.api.LoginApi
import com.polishone.polishproducts.feature.login.data.repository.LoginRepositoryImpl
import com.polishone.polishproducts.feature.login.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginNetworkModule {

    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(loginApi: LoginApi): LoginRepository {
        return LoginRepositoryImpl(loginApi)
    }
}
