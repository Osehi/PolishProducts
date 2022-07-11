package com.polishone.polishproducts.feature.register.domain.usecase

import com.polishone.polishproducts.common.constants.Resource
import com.polishone.polishproducts.feature.register.data.network.model.RegisterRequestBody
import com.polishone.polishproducts.feature.register.data.network.model.RegisterResponse
import com.polishone.polishproducts.feature.register.domain.repository.RegisterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: RegisterRepository) {

    operator fun invoke(registerBody: RegisterRequestBody): Flow<Resource<RegisterResponse>> = flow {
        try {
            emit(Resource.Loading())
            val registerResponse = repository.registerUser(registerBody)
            emit(Resource.Success(registerResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server check your internet connection"))
        }
    }
}
