package com.polishone.polishproducts.feature.createnote.domain.usecase

import com.polishone.polishproducts.common.constants.Resource
import com.polishone.polishproducts.feature.createnote.data.model.CreateNoteRequestBody
import com.polishone.polishproducts.feature.createnote.data.model.CreateNoteResponse
import com.polishone.polishproducts.feature.createnote.domain.repository.CreateNoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreateNoteUsecase @Inject constructor(
    private val createNoteRepository: CreateNoteRepository
) {

    operator fun invoke(createNoteRequestBody: CreateNoteRequestBody): Flow<Resource<CreateNoteResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = createNoteRepository.createNote(createNoteRequestBody)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage))
        } catch (e: IOException){
            emit(Resource.Error("Couldn't reach server check your internet connection"))
        }
    }

}
