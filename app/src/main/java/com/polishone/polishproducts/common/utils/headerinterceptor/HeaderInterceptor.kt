package com.polishone.polishproducts.common.utils.headerinterceptor

import com.polishone.polishproducts.data.sharedpreference.Preferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    private val preferences: Preferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            chain.request()
                .newBuilder()
                .addHeader("Authorization","${preferences.getToken()}")
                .build()
        )
    }
}