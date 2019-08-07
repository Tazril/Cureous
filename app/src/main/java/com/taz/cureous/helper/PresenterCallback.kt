package com.taz.cureous.helper

interface PresenterCallback<T> {
    fun onSucces(responseModel: T)
    fun onFailure(message: String)
}

