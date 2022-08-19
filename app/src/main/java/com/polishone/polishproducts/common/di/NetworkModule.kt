package com.polishone.polishproducts.common.di

import android.content.Context
import com.polishone.polishproducts.common.constants.NetworkConstants
import com.polishone.polishproducts.common.utils.errorhelper.ExceptionHandler
import com.polishone.polishproducts.common.utils.headerinterceptor.HeaderInterceptor
import com.polishone.polishproducts.data.sharedpreference.PolishPreferences
import com.polishone.polishproducts.data.sharedpreference.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    /**
     * provide the logger
     * this logs out every response to and from the web service
     */
    @Provides
    @Singleton
    fun providesLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    /**
     * provide gsonconverter
     */
    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    /**
     * provide the OkHttp
     */
    @Provides
    @Singleton
    fun provideOkHttp(loggingInterceptor: HttpLoggingInterceptor, headerInterceptor: HeaderInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(120L, TimeUnit.SECONDS)
            .readTimeout(120L, TimeUnit.SECONDS)
            .writeTimeout(120L, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()
    }

    /**
     * it provides retrofit http client
     * for available endpoints to use
     */
    @Provides
    @Singleton
    fun providesRetrofitHttpClientForEndpointsToUse(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideErrorHandler(@ApplicationContext context: Context): ExceptionHandler {
        return ExceptionHandler(context)
    }

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): Preferences {
        return PolishPreferences(context)
    }
}
