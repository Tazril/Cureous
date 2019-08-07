package com.taz.cureous.symptomlist.model

import com.taz.cureous.helper.ApiClient
import com.taz.cureous.helper.PresenterCallback
import com.taz.cureous.mvp.BaseProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SymptomListProvider() : BaseProvider<SymptomListResponseModel>() {

    override fun getProviderResponse(callback: PresenterCallback<SymptomListResponseModel>) {
        ApiClient.retroClient.create(SymptomListApi::class.java).getSymptomsListResponse()
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { callback.onSucces(SymptomListResponseModel(it, "Load was succesful")) },
                onError = { callback.onFailure(it.message.toString()) }
            )
    }
}