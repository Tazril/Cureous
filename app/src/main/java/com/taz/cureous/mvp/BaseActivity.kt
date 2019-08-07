package com.taz.cureous.mvp

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity(val layoutId: Int) : AppCompatActivity() {

    abstract var progressBar: ProgressBar
    abstract var swipeRefreshLayout: SwipeRefreshLayout?
    abstract var toolbar: Toolbar?

    abstract fun initActivity()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initActivity()

    }


    infix fun show(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    infix fun showLong(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    infix fun showError(message: String) = Snackbar.make(progressBar, message, Snackbar.LENGTH_SHORT).show()

    fun View.show() {
        visibility = View.VISIBLE
    }

    fun View.hide() {
        visibility = View.INVISIBLE
    }

    fun isConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null  //can also be null in airplane mode
    }
}