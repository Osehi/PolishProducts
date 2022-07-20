package com.polishone.polishproducts.common.utils.extensions

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.polishone.polishproducts.R

fun Fragment.myDialog(): AlertDialog {

    val inflateDialogLayout = requireActivity().layoutInflater
    val dialogView = inflateDialogLayout.inflate(R.layout.progress_dialog, null)
    val builder = AlertDialog.Builder(requireActivity())
    builder.setView(dialogView)
    builder.setCancelable(false)
    return builder.create()
}
