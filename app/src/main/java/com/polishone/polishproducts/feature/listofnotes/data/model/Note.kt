package com.polishone.polishproducts.feature.listofnotes.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val content: String?,
    val id: String?,
    val isCompleted: Boolean?,
    val taskPriority: Int?,
    val title: String?
): Parcelable