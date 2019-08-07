package com.taz.cureous.symptomlist.model

import com.taz.cureous.globalmodels.Symptom

data class SymptomListResponseModel(val symptomsList: List<Symptom>?, val status: String?)