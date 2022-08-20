package com.polishone.polishproducts.feature.listofnotes.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polishone.polishproducts.common.constants.Resource
import com.polishone.polishproducts.feature.listofnotes.data.model.GetTaskResponse
import com.polishone.polishproducts.feature.listofnotes.domain.usecase.GetTasksUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetTasksViewModel @Inject constructor(
    private val getTasksUsecase: GetTasksUsecase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _getTasksResponse = MutableSharedFlow<Resource<GetTaskResponse>>()
    val getTasksResponse: SharedFlow<Resource<GetTaskResponse>> get() = _getTasksResponse.asSharedFlow()

    fun getAllTasks() {
        viewModelScope.launch {
            getTasksUsecase().collect {
                _getTasksResponse.emit(it)
            }
        }
    }
}