package com.polishone.polishproducts.feature.login.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.polishone.polishproducts.feature.login.domain.usecase.LoginUsecase
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUsecase: LoginUsecase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){



}