package com.polishone.polishproducts.feature.userprofile.data.network.repository

import com.polishone.polishproducts.feature.userprofile.data.network.api.GetUserProfileApi
import com.polishone.polishproducts.feature.userprofile.data.network.model.GetUserProfileResponse
import com.polishone.polishproducts.feature.userprofile.domain.repository.GetUserProfileRepository
import javax.inject.Inject

class GetUserProfileRepositoryImpl @Inject constructor(
    private val getUserProfileApi: GetUserProfileApi
) : GetUserProfileRepository {
    override suspend fun getUserProfile(): GetUserProfileResponse {
        return getUserProfileApi.getUserProfile()
    }
}
