package com.polishone.polishproducts.feature.login.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polishone.polishproducts.common.constants.Resource
import com.polishone.polishproducts.feature.login.data.network.model.LoginRequestBody
import com.polishone.polishproducts.feature.login.data.network.model.LoginResponse
import com.polishone.polishproducts.feature.login.domain.usecase.LoginUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUsecase: LoginUsecase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _loginResponse = MutableSharedFlow<Resource<LoginResponse>>()
    val loginResponse: SharedFlow<Resource<LoginResponse>> get() = _loginResponse.asSharedFlow()

    fun getUserLoggeIn(loginRequestBody: LoginRequestBody) {
        viewModelScope.launch {
            loginUsecase(loginRequestBody).collect {
                _loginResponse.emit(it)
            }
        }
    }
}
