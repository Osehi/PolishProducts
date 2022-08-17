package com.polishone.polishproducts.common.utils.uihelpers

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard(){
    val hideKeyboard = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var currentFocus = requireActivity().currentFocus
    if (currentFocus == null){
        currentFocus = View(requireActivity())
    }
    hideKeyboard.hideSoftInputFromWindow(currentFocus.windowToken, 0)
}

fun Fragment.setPriorityToNumber(priority: String): Int {
   val priorityNum: Int = when(priority) {
        "Low" -> 1
        "Medium" -> 2
        "High" -> 3
       else -> 0
    }
    return priorityNum
}