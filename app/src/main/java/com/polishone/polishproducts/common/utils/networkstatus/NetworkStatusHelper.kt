package com.polishone.polishproducts.common.utils.networkstatus

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NetworkStatusHelper(private val context: Context) : LiveData<NetworkStatus>() {

    val validNetworkConnections: ArrayList<Network> = ArrayList()

    var connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private lateinit var connectivityManagerCallback: ConnectivityManager.NetworkCallback

    fun announceStatus() {
        if (validNetworkConnections.isNotEmpty()) {
            postValue(NetworkStatus.Available)
        } else {
            postValue(NetworkStatus.Unavailable)
        }
    }

    fun getConnectivityManagerCallBack() =
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                val networkCapability = connectivityManager.getNetworkCapabilities(network)
                val hasNetworkConnection = networkCapability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
                if (hasNetworkConnection) {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (InternetAvailability.check()) {
                            withContext(Dispatchers.Main) {
                                validNetworkConnections.add(network)
                                announceStatus()
                            }
                        }
                    }
                }
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                validNetworkConnections.remove(network)
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (InternetAvailability.check()) {
                            withContext(Dispatchers.Main) {
                                validNetworkConnections.add(network)
                                announceStatus()
                            }
                        }
                    }
                } else {
                    validNetworkConnections.remove(network)
                }
                announceStatus()
            }
        }

    override fun onActive() {
        super.onActive()
        connectivityManagerCallback = getConnectivityManagerCallBack()
        val networkRequest = NetworkRequest
            .Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, connectivityManagerCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManagerCallback = getConnectivityManagerCallBack()
        connectivityManager.unregisterNetworkCallback(connectivityManagerCallback)
    }
}
