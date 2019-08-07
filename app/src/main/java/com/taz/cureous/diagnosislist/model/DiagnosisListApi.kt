package com.taz.cureous.diagnosislist.model

import com.taz.cureous.helper.Urls
import com.taz.cureous.helper.Urls.LANGUAGE
import com.taz.cureous.helper.Urls.TOKEN
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DiagnosisListApi {

    @GET(Urls.DIAGNOSIS)
    fun getIssuesListResponse(
        @Query("symptoms") symptomIdList: String,
        @Query("gender") gender: String,
        @Query("year_of_birth") yearOfBirth: Int,
        @Query("token") defToken: String = TOKEN,
        @Query("language") defLang: String = LANGUAGE
    ): Single<List<IssueSpecialisation>>
}

