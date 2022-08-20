package com.polishone.polishproducts.feature.listofnotes.domain.repository

import com.polishone.polishproducts.feature.listofnotes.data.model.GetTaskResponse

interface GetTasksRepository {
    suspend fun getTasks(): GetTaskResponse
}