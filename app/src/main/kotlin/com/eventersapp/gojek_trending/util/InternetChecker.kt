package com.eventersapp.gojek_trending.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import timber.log.Timber


interface InternetChecker {
    fun isOnline(): Boolean
}

class InternetCheckerImpl(private val context: Context) : InternetChecker {
    override fun isOnline(): Boolean {
        try {
            val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo: NetworkInfo = connectivityManager.getActiveNetworkInfo()
            val connected = networkInfo != null && networkInfo.isAvailable &&
                    networkInfo.isConnected
            return connected
        } catch (e: Exception) {
            Timber.v("connectivity${e.message}")
        }
        return false
    }
}