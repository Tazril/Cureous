package com.taz.cureous.specialisationlist.contract

import com.taz.cureous.mvp.BasePresenter
import com.taz.cureous.specialisationlist.model.SpecialisationListProvider
import com.taz.cureous.specialisationlist.model.SpecialisationListResponseModel

class SpecialisationListPresenter(
    view: SpecialisationListView,
    provider: SpecialisationListProvider
) : BasePresenter<SpecialisationListResponseModel>(view, provider) {


}