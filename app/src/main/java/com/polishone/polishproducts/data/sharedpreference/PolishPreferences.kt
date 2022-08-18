package com.polishone.polishproducts.data.sharedpreference

import android.content.Context
import javax.inject.Inject

class PolishPreferences @Inject constructor(context: Context) : Preferences {

    companion object {
        const val PREFERENCES_NAME = "POLISH_PREFERENCES"
    }

    private val prefences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun putToken(token: String) {
        prefences.edit().putString(PreferenceConstant.KEY_TOKEN, token).apply()
    }

    override fun getToken(): String {
        return prefences.getString(PreferenceConstant.KEY_TOKEN, "").orEmpty()
    }
}
