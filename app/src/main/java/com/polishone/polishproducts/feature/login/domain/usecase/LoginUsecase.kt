package com.polishone.polishproducts.feature.login.domain.usecase

import com.polishone.polishproducts.common.constants.Resource
import com.polishone.polishproducts.feature.login.data.network.model.LoginRequestBody
import com.polishone.polishproducts.feature.login.data.network.model.LoginResponse
import com.polishone.polishproducts.feature.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUsecase @Inject constructor(private val loginRepository: LoginRepository) {

    operator fun invoke(loginRequestBody: LoginRequestBody): Flow<Resource<LoginResponse>> = flow {
        try {
            emit(Resource.Loading())
            val loginResponse = loginRepository.getUserLoggedIn(loginRequestBody)
            emit(Resource.Success(loginResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server check your internet connection"))
        }
    }
}
