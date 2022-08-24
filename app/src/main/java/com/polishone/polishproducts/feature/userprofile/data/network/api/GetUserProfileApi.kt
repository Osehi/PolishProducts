package com.polishone.polishproducts.feature.userprofile.data.network.api

import com.polishone.polishproducts.feature.userprofile.data.network.model.GetUserProfileResponse
import retrofit2.http.GET

interface GetUserProfileApi {
    @GET("api/user/profile")
    suspend fun getUserProfile(): GetUserProfileResponse
}