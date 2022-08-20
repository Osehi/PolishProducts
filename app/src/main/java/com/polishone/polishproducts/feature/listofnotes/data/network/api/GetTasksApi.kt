package com.polishone.polishproducts.feature.listofnotes.data.network.api

import com.polishone.polishproducts.feature.listofnotes.data.model.GetTaskResponse
import retrofit2.http.GET

interface GetTasksApi {

    @GET("api/note")
    suspend fun getTasks(): GetTaskResponse

}