package com.taz.cureous.helper

import io.reactivex.Single
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginTokenApi {

    @Headers(Urls.DHEADER)
    @POST(Urls.DLOGIN)
    fun getToken(): Single<Token>
}
