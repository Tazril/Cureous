package com.taz.cureous.helper

import io.reactivex.Single
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * This API serves login and provide access token as Single Observable object of RxJava library
 */
interface LoginTokenApi {

    @Headers(Urls.HEADER)
    @POST(Urls.LOGIN)
    fun getToken(): Single<Token>
}
