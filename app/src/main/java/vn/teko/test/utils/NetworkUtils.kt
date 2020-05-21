package vn.teko.test.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {

    @JvmStatic
    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    @JvmStatic
    fun is3GOn(context: Context): Boolean {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                false
            } else activeNetwork.type == ConnectivityManager.TYPE_MOBILE
        } else false
        // not connected to the internet
    }
}