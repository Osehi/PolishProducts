package com.polishone.polishproducts.data.sharedpreference

interface Preferences {

    fun putToken(token: String)

    fun getToken(): String
}
