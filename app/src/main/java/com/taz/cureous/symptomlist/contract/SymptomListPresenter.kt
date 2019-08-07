package com.taz.cureous.symptomlist.contract

import com.taz.cureous.mvp.BasePresenter
import com.taz.cureous.symptomlist.model.SymptomListProvider
import com.taz.cureous.symptomlist.model.SymptomListResponseModel

class SymptomListPresenter(
    view: SymptomListView,
    provider: SymptomListProvider
) : BasePresenter<SymptomListResponseModel>(view, provider) {


}