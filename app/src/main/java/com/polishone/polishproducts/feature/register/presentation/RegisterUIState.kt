package com.polishone.polishproducts.feature.register.presentation

data class RegisterUIState(
    val isLoading: Boolean = false,
    val responseFromServer: String = "",
    val error: String = ""
)
