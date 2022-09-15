package com.polishone.polishproducts.feature.listofnotes.presentation

import android.view.View
import com.polishone.polishproducts.feature.listofnotes.data.model.Note

interface TaskClicker {

    fun onclickItem(currentTask: Note, position: Int)

    fun onClickItemEllipses(currentTask: Note, position: Int, view: View)
}
