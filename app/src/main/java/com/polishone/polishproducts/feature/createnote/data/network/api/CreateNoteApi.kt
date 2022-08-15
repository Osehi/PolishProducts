package com.polishone.polishproducts.feature.createnote.data.network.api

import com.polishone.polishproducts.feature.createnote.data.model.CreateNoteRequestBody
import com.polishone.polishproducts.feature.createnote.data.model.CreateNoteResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateNoteApi {

    @POST("api/note")
    fun createNote(
        @Body createNoteRequestBody: CreateNoteRequestBody
    ): CreateNoteResponse
}
