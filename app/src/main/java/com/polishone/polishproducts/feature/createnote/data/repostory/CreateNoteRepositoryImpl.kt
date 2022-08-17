package com.polishone.polishproducts.feature.createnote.data.repostory

import com.polishone.polishproducts.feature.createnote.data.model.CreateNoteRequestBody
import com.polishone.polishproducts.feature.createnote.data.model.CreateNoteResponse
import com.polishone.polishproducts.feature.createnote.data.network.api.CreateNoteApi
import com.polishone.polishproducts.feature.createnote.domain.repository.CreateNoteRepository
import javax.inject.Inject

class CreateNoteRepositoryImpl @Inject constructor(private val createNoteApi: CreateNoteApi) : CreateNoteRepository {
    override suspend fun createNote(createNoteRequestBody: CreateNoteRequestBody): CreateNoteResponse {
        return createNoteApi.createNote(createNoteRequestBody)
    }
}