package com.polishone.polishproducts.feature.createnote.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polishone.polishproducts.common.constants.Resource
import com.polishone.polishproducts.feature.createnote.data.model.CreateNoteRequestBody
import com.polishone.polishproducts.feature.createnote.data.model.CreateNoteResponse
import com.polishone.polishproducts.feature.createnote.domain.usecase.CreateNoteUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    private val createNoteUsecase: CreateNoteUsecase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _createNoteResponse = MutableSharedFlow<Resource<CreateNoteResponse>>()
    val createNoteResponse: SharedFlow<Resource<CreateNoteResponse>> get() = _createNoteResponse.asSharedFlow()

    fun userCreateNote(createNoteRequestBody: CreateNoteRequestBody) {
        viewModelScope.launch {
            createNoteUsecase(createNoteRequestBody).collect {
                _createNoteResponse.emit(it)
            }
        }
    }

}















































































































































