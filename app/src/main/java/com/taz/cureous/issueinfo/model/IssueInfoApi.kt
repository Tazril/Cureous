package com.taz.cureous.issueinfo.model

import com.taz.cureous.globalmodels.Issue
import com.taz.cureous.helper.Urls
import com.taz.cureous.helper.Urls.LANGUAGE
import com.taz.cureous.helper.Urls.TOKEN
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IssueInfoApi {

    @GET(Urls.ISSUE_INFO)
    fun getIssuesListResponse(
        @Path(Urls.ID) id: Int,
        @Query("token") defToken: String = TOKEN,
        @Query("language") defLang: String = LANGUAGE
    ): Single<Issue>
}
