package com.taz.cureous.issuelist.model

import com.taz.cureous.helper.ApiClient
import com.taz.cureous.helper.PresenterCallback
import com.taz.cureous.mvp.BaseProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class IssueListProvider() : BaseProvider<IssueListResponseModel>() {


    override fun getProviderResponse(callback: PresenterCallback<IssueListResponseModel>) {

        ApiClient.retroClient.create(IssueListApi::class.java).getIssuesListResponse()
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { callback.onSucces(IssueListResponseModel(it, "Load was succesful")) },
                onError = { callback.onFailure(it.message.toString()) }
            )
    }


}