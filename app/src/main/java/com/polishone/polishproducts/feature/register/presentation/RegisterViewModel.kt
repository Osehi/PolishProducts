package com.polishone.polishproducts.feature.register.presentation

import androidx.lifecycle.*
import com.polishone.polishproducts.common.constants.Resource
import com.polishone.polishproducts.feature.register.data.network.model.RegisterRequestBody
import com.polishone.polishproducts.feature.register.data.network.model.RegisterResponse
import com.polishone.polishproducts.feature.register.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    /*
    private val _registerResponse = MutableStateFlow<Resource<RegisterResponse>>(Resource.Loading())
    val registerResponse: StateFlow<Resource<RegisterResponse>> get() = _registerResponse

    fun getRegistered(registerBody: RegisterRequestBody) {
        viewModelScope.launch {
            registerUseCase(registerBody).collect {
                _registerResponse.value = it
            }
        }
    }

     */

    private val _registerResponse = MutableSharedFlow<Resource<RegisterResponse>>()
    val registerResponse: SharedFlow<Resource<RegisterResponse>> get() = _registerResponse.asSharedFlow()

    fun getRegistered(registerBody: RegisterRequestBody) {
        viewModelScope.launch {
            registerUseCase(registerBody).collect {
                _registerResponse.emit(it)
            }
        }
    }
}
