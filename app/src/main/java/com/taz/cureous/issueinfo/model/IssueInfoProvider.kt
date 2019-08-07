package com.taz.cureous.issueinfo.model

import com.taz.cureous.helper.ApiClient
import com.taz.cureous.helper.PresenterCallback
import com.taz.cureous.mvp.BaseProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class IssueInfoProvider(val issueId: Int) : BaseProvider<IssueInfoResponseModel>() {


    override fun getProviderResponse(callback: PresenterCallback<IssueInfoResponseModel>) {
        ApiClient.retroClient.create(IssueInfoApi::class.java).getIssuesListResponse(issueId)
            .subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        )
            .subscribeBy(
                onSuccess = { callback.onSucces(IssueInfoResponseModel(it, "Load was succesful")) },
                onError = { callback.onFailure(it.message.toString()) }
            )
    }


}