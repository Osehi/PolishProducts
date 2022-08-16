package com.polishone.polishproducts.common.utils.stattisticdata

object PriorityData {

    val priorityData: ArrayList<String>
        get() {
            val priorityContent = ArrayList<String>()
            priorityContent.add("Low")
            priorityContent.add("Medium")
            priorityContent.add("High")

            return priorityContent
        }
}
