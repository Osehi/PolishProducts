package com.polishone.polishproducts.feature.userprofile.domain.repository

import com.polishone.polishproducts.feature.userprofile.data.network.model.GetUserProfileResponse

interface GetUserProfileRepository {
    suspend fun getUserProfile(): GetUserProfileResponse
}