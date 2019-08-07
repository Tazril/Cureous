package com.taz.cureous.diagnosislist.model

import com.taz.cureous.helper.ApiClient
import com.taz.cureous.helper.Constant.DEF_YEAR_OF_BIRTH
import com.taz.cureous.helper.PresenterCallback
import com.taz.cureous.mvp.BaseProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DiagnosisListProvider() : BaseProvider<DiagnosisListResponseModel>() {
    lateinit var sIdList: String
    lateinit var gender: String
    var yob: Int = DEF_YEAR_OF_BIRTH

    override fun getProviderResponse(callback: PresenterCallback<DiagnosisListResponseModel>) {
        ApiClient.retroClient.create(DiagnosisListApi::class.java)
            .getIssuesListResponse(symptomIdList = sIdList, gender = gender, yearOfBirth = yob)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { callback.onSucces(DiagnosisListResponseModel(it, "Load was succesful")) },
                onError = { callback.onFailure(it.message.toString()) }
            )
    }


}