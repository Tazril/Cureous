package com.taz.cureous.diagnosislist.contract

import com.taz.cureous.diagnosislist.model.DiagnosisListProvider
import com.taz.cureous.diagnosislist.model.DiagnosisListResponseModel
import com.taz.cureous.mvp.BasePresenter

class DiagnosisListPresenter(
    view: DiagnosisListView,
    provider: DiagnosisListProvider
) : BasePresenter<DiagnosisListResponseModel>(view, provider) {


}