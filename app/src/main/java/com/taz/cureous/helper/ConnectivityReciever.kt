package com.taz.cureous.helper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

/**
 * This is an implemenation of ConnectivityReciever to trigger actions on connection restored
 */
class ConnectivityReciever() : BroadcastReceiver() {

    private var listener: Listener? = null

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    override fun onReceive(context: Context, intent: Intent) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo
        if (info != null && listener != null) {
            listener!!.connectionRestored(true)
            listener!!.networkType(info.extraInfo)
        } else if (listener != null) {
            listener!!.connectionRestored(false)
            listener!!.networkType("None")
        }
    }

    interface Listener {
        fun connectionRestored(status: Boolean)
        fun networkType(type: String)
    }


}
