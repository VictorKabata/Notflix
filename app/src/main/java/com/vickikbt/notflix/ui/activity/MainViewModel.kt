package com.vickikbt.notflix.ui.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.ViewModel
import com.vickikbt.notflix.util.NetState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(context: Context) : ViewModel() {

    private val _networkAvailable: MutableStateFlow<NetState> =
        MutableStateFlow(NetState.NetworkUnknown)
    val isNetworkOn: StateFlow<NetState> get() = _networkAvailable

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val networkRequest: NetworkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()
    private val networkCallback: ConnectivityManager.NetworkCallback =
        object : ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                super.onLost(network)
                _networkAvailable.value = NetState.NetworkOff
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                _networkAvailable.value = NetState.NetworkOn
            }
        }

    fun registerCallback() {
        _networkAvailable.value = NetState.NetworkUnknown
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }
}
