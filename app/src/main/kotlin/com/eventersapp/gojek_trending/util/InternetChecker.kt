package com.eventersapp.gojek_trending.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import timber.log.Timber
import javax.inject.Inject


interface InternetChecker {
    fun isInternetAvailable(): Boolean
}

class InternetCheckerImpl @Inject constructor(private val context: Context) : InternetChecker {

    override fun isInternetAvailable(): Boolean =
        try {
            val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo: NetworkInfo = connectivityManager.getActiveNetworkInfo()
            val connected = networkInfo != null && networkInfo.isAvailable &&
                    networkInfo.isConnected
            connected
        } catch (e: Exception) {
            Timber.v("connectivity${e.message}")
            false
        }

}