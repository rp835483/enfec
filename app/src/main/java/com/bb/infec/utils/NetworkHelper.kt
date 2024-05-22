package com.bb.infec.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkHelper {
    companion object
    {
        fun isNetworkAvailable(context: Context):Boolean
        {

            val manager =
                (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)!!
            val networkInfo = manager!!.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
            return true
        }


    }

}