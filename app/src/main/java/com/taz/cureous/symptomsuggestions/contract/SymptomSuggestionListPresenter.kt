package com.taz.cureous.symptomsuggestions.contract

import com.taz.cureous.mvp.BasePresenter
import com.taz.cureous.symptomsuggestions.model.SymptomSuggestionListProvider
import com.taz.cureous.symptomsuggestions.model.SymptomSuggestionResponseModel

class SymptomSuggestionListPresenter(
    viewSuggestion: SymptomSuggestionListView,
    providerSuggestion: SymptomSuggestionListProvider
) : BasePresenter<SymptomSuggestionResponseModel>(viewSuggestion, providerSuggestion) {


}