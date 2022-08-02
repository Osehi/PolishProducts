package com.polishone.polishproducts.feature.createnote.data.repostory

import com.polishone.polishproducts.feature.createnote.data.model.CreateNoteRequestBody
import com.polishone.polishproducts.feature.createnote.data.model.CreateNoteResponse
import com.polishone.polishproducts.feature.createnote.domain.repository.CreateNoteRepository
import javax.inject.Inject

class CreateNoteRepositoryImpl @Inject constructor() : CreateNoteRepository {
    override fun createNote(createNoteRequestBody: CreateNoteRequestBody): CreateNoteResponse {
        TODO("Not yet implemented")
    }
}