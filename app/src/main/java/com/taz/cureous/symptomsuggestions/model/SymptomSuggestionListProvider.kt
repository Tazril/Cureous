package com.taz.cureous.symptomsuggestions.model

import com.taz.cureous.helper.ApiClient
import com.taz.cureous.helper.Constant.DEF_YEAR_OF_BIRTH
import com.taz.cureous.helper.PresenterCallback
import com.taz.cureous.mvp.BaseProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SymptomSuggestionListProvider() :
    BaseProvider<SymptomSuggestionResponseModel>() {
    lateinit var sIdList: String
    lateinit var gender: String
    var yob: Int = DEF_YEAR_OF_BIRTH
    override fun getProviderResponse(callback: PresenterCallback<SymptomSuggestionResponseModel>) {
        ApiClient.retroClient.create(SymptomSuggestionListApi::class.java).getSymptomsListResponse(
            symptomIdList = sIdList, gender = gender, yearOfBirth = yob
        )
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { callback.onSucces(SymptomSuggestionResponseModel(it, "Load was succesful")) },
                onError = { callback.onFailure(it.message.toString()) }
            )
    }
}