package com.taz.cureous.symptomsuggestions.model

import com.taz.cureous.globalmodels.Symptom

data class SymptomSuggestionResponseModel(val symptomsList: List<Symptom>?, val status: String?)