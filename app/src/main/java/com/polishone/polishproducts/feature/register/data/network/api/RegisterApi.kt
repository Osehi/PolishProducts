package com.polishone.polishproducts.feature.register.data.network.api

import com.polishone.polishproducts.feature.register.data.network.model.RegisterRequestBody
import com.polishone.polishproducts.feature.register.data.network.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {

    @POST("/api/register")
    suspend fun registerUser(
        @Body registerRequest: RegisterRequestBody
    ): RegisterResponse
}