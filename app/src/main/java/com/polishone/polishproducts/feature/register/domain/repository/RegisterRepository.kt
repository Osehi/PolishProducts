package com.polishone.polishproducts.feature.register.domain.repository

import com.polishone.polishproducts.feature.register.data.network.model.RegisterRequestBody
import com.polishone.polishproducts.feature.register.data.network.model.RegisterResponse

interface RegisterRepository {

    suspend fun registerUser(registerRequest: RegisterRequestBody): RegisterResponse
}
