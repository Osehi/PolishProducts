package com.polishone.polishproducts.feature.createnote.data.model

data class CreateNoteRequestBody(
    val content: String?,
    val taskPriority: Int?,
    val title: String?
)