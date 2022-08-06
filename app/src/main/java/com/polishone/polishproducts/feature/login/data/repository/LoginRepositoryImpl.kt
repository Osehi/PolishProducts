package com.polishone.polishproducts.feature.login.data.repository

import com.polishone.polishproducts.feature.login.data.network.api.LoginApi
import com.polishone.polishproducts.feature.login.data.network.model.LoginRequestBody
import com.polishone.polishproducts.feature.login.data.network.model.LoginResponse
import com.polishone.polishproducts.feature.login.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi
) : LoginRepository {
    override suspend fun getUserLoggedIn(loginRequestBody: LoginRequestBody): LoginResponse {
        return loginApi.loginUser(loginRequestBody)
    }
}