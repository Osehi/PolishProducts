package com.polishone.polishproducts.common.utils.networkstatus

sealed class NetworkStatus {
    object Available : NetworkStatus()
    object Unavailable : NetworkStatus()
}
