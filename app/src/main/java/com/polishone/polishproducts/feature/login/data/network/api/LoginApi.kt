package com.polishone.polishproducts.feature.login.data.network.api

import com.polishone.polishproducts.feature.login.data.network.model.LoginRequestBody
import com.polishone.polishproducts.feature.login.data.network.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("api/login")
    suspend fun loginUser(
        @Body loginRequestBody: LoginRequestBody
    ): LoginResponse
}
