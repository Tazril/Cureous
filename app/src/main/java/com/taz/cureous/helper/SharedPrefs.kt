package com.taz.cureous.helper

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPrefs {

    lateinit var sharedPrefs: SharedPreferences

    fun instantiate(context: Context): SharedPrefs {
        sharedPrefs = context.getSharedPreferences("SharedPrefs", MODE_PRIVATE)
        return this
    }

    fun putString(key: String, value: String): SharedPrefs {
        val editor = sharedPrefs.edit()
        editor.putString(key, value)
        editor.apply()
        return this
    }

    fun getString(key: String): String? = sharedPrefs.getString(key, "null")


}