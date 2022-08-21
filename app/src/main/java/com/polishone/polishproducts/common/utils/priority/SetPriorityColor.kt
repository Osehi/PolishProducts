package com.polishone.polishproducts.common.utils.priority

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

object SetPriorityColor {
    fun setDrawableColor(context: Context, drawable: Drawable, color: Int) {
        val drawableWrap = DrawableCompat.wrap(drawable)
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(context, color))
    }
}