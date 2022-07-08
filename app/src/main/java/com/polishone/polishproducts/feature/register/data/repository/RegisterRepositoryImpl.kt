package com.polishone.polishproducts.feature.register.data.repository

import com.polishone.polishproducts.feature.register.data.network.api.RegisterApi
import com.polishone.polishproducts.feature.register.data.network.model.RegisterRequestBody
import com.polishone.polishproducts.feature.register.data.network.model.RegisterResponse
import com.polishone.polishproducts.feature.register.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val registerApi: RegisterApi) : RegisterRepository{
    override suspend fun registerUser(registerRequest: RegisterRequestBody): RegisterResponse {
        return registerApi.registerUser(registerRequest)
    }
}