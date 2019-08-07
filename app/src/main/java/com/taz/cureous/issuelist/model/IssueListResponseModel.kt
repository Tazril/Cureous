package com.taz.cureous.issuelist.model

import com.taz.cureous.globalmodels.Issue

data class IssueListResponseModel(val issuesList: List<Issue>?, val status: String?)