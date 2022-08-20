package com.polishone.polishproducts.feature.listofnotes.domain.usecase

import com.polishone.polishproducts.common.constants.Resource
import com.polishone.polishproducts.common.utils.errorhelper.ExceptionHandler
import com.polishone.polishproducts.feature.listofnotes.data.model.GetTaskResponse
import com.polishone.polishproducts.feature.listofnotes.domain.repository.GetTasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetTasksUsecase @Inject constructor(
    private val getTasksRepository: GetTasksRepository,
    private val exceptionHandler: ExceptionHandler
) {
    operator fun invoke(): Flow<Resource<GetTaskResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = getTasksRepository.getTasks()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            val message = exceptionHandler.parse(e)
            emit(Resource.Error(message))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server check your internet connection"))
        }
    }
}