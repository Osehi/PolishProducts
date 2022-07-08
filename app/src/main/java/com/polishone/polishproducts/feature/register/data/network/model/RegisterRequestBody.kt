package com.polishone.polishproducts.feature.register.data.network.model

data class RegisterRequestBody(
    val email: String?,
    val name: String?,
    val password: String?
)