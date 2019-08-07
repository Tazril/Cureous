package com.taz.cureous.diagnosislist.model

import com.taz.cureous.globalmodels.Issue
import com.taz.cureous.globalmodels.Specialisation

data class IssueSpecialisation(
    val Issue: Issue,
    val Specialisation: List<Specialisation>
)

data class DiagnosisListResponseModel(val issuesSpecsList: List<IssueSpecialisation>?, val status: String?)