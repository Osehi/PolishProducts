package com.polishone.polishproducts.common.utils.priority

import com.polishone.polishproducts.R

enum class PriorityColor {
    LOW, MEDIUM, HIGH;
    fun getColor() = when (this) {
        LOW -> R.color.priorityLow
        MEDIUM -> R.color.priorityMedium
        HIGH -> R.color.priorityHigh
    }
}