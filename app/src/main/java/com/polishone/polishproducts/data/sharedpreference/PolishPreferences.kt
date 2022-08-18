package com.polishone.polishproducts.data.sharedpreference

import android.content.Context
import javax.inject.Inject

class PolishPreferences @Inject constructor(context: Context) : Preferences {

    companion object {
        const val PREFERENCES_NAME = "DECAPAY_PREFERENCES"
    }

    override fun putToken(token: String) {
        //
    }

    override fun getToken(): String {
        //
    }
}
