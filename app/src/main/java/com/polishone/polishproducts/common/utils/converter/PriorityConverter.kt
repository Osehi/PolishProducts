package com.polishone.polishproducts.common.utils.converter

object PriorityConverter {

    fun convertPriorityNumberToString(receivePriority: Int) : String{
         val priority = when(receivePriority) {
            1 -> "Low"
            2 -> "Medium"
             else -> {"High"}
         }
        return priority
    }
}