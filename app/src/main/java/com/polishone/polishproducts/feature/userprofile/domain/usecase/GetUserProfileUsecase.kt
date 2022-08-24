package com.polishone.polishproducts.feature.userprofile.domain.usecase

import com.polishone.polishproducts.common.constants.Resource
import com.polishone.polishproducts.feature.userprofile.data.network.model.GetUserProfileResponse
import com.polishone.polishproducts.feature.userprofile.domain.repository.GetUserProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetUserProfileUsecase @Inject constructor(
    private val getUserProfileRepository: GetUserProfileRepository
) {
    operator fun invoke(): Flow<Resource<GetUserProfileResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = getUserProfileRepository.getUserProfile()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}