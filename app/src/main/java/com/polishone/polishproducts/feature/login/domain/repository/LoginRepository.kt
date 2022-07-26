package com.polishone.polishproducts.feature.login.domain.repository

import com.polishone.polishproducts.feature.login.data.network.model.LoginRequestBody
import com.polishone.polishproducts.feature.login.data.network.model.LoginResponse

interface LoginRepository {

    suspend fun getUserLoggedIn(LoginRequest: LoginRequestBody): LoginResponse
}
