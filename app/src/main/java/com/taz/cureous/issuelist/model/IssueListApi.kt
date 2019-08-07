package com.taz.cureous.issuelist.model

import com.taz.cureous.globalmodels.Issue
import com.taz.cureous.helper.Urls
import com.taz.cureous.helper.Urls.LANGUAGE
import com.taz.cureous.helper.Urls.TOKEN
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IssueListApi {

    @GET(Urls.ISSUE_LIST)
    fun getIssuesListResponse(
        @Query("token") defToken: String = TOKEN,
        @Query("language") defLang: String = LANGUAGE
    ): Single<List<Issue>>
}

