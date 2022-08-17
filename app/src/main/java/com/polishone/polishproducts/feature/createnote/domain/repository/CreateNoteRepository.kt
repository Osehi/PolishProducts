package com.polishone.polishproducts.feature.createnote.domain.repository

import com.polishone.polishproducts.feature.createnote.data.model.CreateNoteRequestBody
import com.polishone.polishproducts.feature.createnote.data.model.CreateNoteResponse

interface CreateNoteRepository {

    suspend fun createNote(createNoteRequestBody: CreateNoteRequestBody): CreateNoteResponse
}
