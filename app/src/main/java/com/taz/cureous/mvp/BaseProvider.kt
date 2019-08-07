package com.taz.cureous.mvp

import com.taz.cureous.helper.PresenterCallback

abstract class BaseProvider<T>() {

    abstract fun getProviderResponse(callback: PresenterCallback<T>)

}