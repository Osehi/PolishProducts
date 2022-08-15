package com.polishone.polishproducts.feature.createnote.data.model

data class CreateNoteResponse(
    val content: String?,
    val id: String?,
    val isCompleted: Boolean?,
    val taskPriority: Int?,
    val title: String?,
    val userId: String?
)