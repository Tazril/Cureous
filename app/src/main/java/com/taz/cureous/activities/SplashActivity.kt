package com.taz.cureous.activities

import android.content.Intent
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.taz.cureous.R
import com.taz.cureous.helper.ApiClient
import com.taz.cureous.helper.LoginTokenApi
import com.taz.cureous.helper.SharedPrefs
import com.taz.cureous.helper.Urls
import com.taz.cureous.mvp.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity(R.layout.activity_splash) {

    override lateinit var progressBar: ProgressBar
    override var swipeRefreshLayout: SwipeRefreshLayout? = null
    override var toolbar: Toolbar? = null

    override fun initActivity() {
        progressBar = progressbarSplash; loadToken()
        refreshBtnSplash.setOnClickListener { loadToken() }
    }


    private fun loadToken() {
        if (!isConnected()) {
            showError("Not Connected to Internet"); return
        }
        refreshBtnSplash.hide(); progressBar.show()
        ApiClient.instantiate(this).retroClient.create(LoginTokenApi::class.java).getToken()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    SharedPrefs().instantiate(this).putString("token", it.token)
                    Urls.TOKEN = it.token; progressBar.hide(); show("Welcome"); finish()
                },
                onError = {
                    showError(it.message!!); refreshBtnSplash.show(); progressBar.hide()
                }
            )
    }


}
