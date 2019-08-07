package com.taz.cureous.mvp

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

abstract class BaseFragment<T>() : Fragment() {

    abstract val layoutId: Int
    abstract fun loadResponse(responseModel: T)
    abstract fun initView()

    lateinit var baseActivity: BaseActivity
    var fragmentView: View? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(layoutId, container, false)
        baseActivity = (activity as BaseActivity)
        baseActivity.swipeRefreshLayout?.setOnRefreshListener {
            Handler().postDelayed({
                initView();
                baseActivity.swipeRefreshLayout?.isRefreshing = false
            }, 3000)
        }
        return fragmentView
    }

    infix fun show(message: String) = baseActivity.show(message)
    infix fun showError(message: String) = baseActivity.showError(message)
    infix fun showLong(message: String) = baseActivity.showLong(message)


    fun hideKeyboard() {
        baseActivity.currentFocus.let {
            val imm = baseActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it?.windowToken, 0)
        }
    }


    fun showProgressBar() {
        baseActivity.progressBar.show()
    }

    fun hideProgressBar() {
        baseActivity.progressBar.hide()
    }

    fun View.show() {
        visibility = View.VISIBLE
    }

    fun View.hide() {
        visibility = View.INVISIBLE
    }

    fun View.gone() {
        visibility = View.GONE
    }

    fun isConnected(): Boolean = baseActivity.isConnected()
}