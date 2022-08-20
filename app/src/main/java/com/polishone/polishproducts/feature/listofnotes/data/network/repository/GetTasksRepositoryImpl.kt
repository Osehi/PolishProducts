package com.polishone.polishproducts.feature.listofnotes.data.network.repository

import com.polishone.polishproducts.feature.listofnotes.data.model.GetTaskResponse
import com.polishone.polishproducts.feature.listofnotes.data.network.api.GetTasksApi
import com.polishone.polishproducts.feature.listofnotes.domain.repository.GetTasksRepository
import javax.inject.Inject

class GetTasksRepositoryImpl @Inject constructor(
    private val getTasksApi: GetTasksApi
) : GetTasksRepository {
    override suspend fun getTasks(): GetTaskResponse {
        return getTasksApi.getTasks()
    }
}