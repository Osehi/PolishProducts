package com.polishone.polishproducts.feature.register.presentation

import androidx.lifecycle.*
import com.polishone.polishproducts.common.constants.Resource
import com.polishone.polishproducts.feature.register.data.network.model.RegisterRequestBody
import com.polishone.polishproducts.feature.register.data.network.model.RegisterResponse
import com.polishone.polishproducts.feature.register.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _registerResponse = MutableLiveData<Resource<RegisterResponse>>()
    val registerResponse: LiveData<Resource<RegisterResponse>> get() = _registerResponse

    fun getRegistered(registerBody: RegisterRequestBody) {
        viewModelScope.launch { registerUseCase(registerBody).collect {
            _registerResponse.value = it
        }
        }
    }
}
