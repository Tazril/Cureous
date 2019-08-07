package com.taz.cureous.issueinfo.contract

import com.taz.cureous.issueinfo.model.IssueInfoProvider
import com.taz.cureous.issueinfo.model.IssueInfoResponseModel
import com.taz.cureous.mvp.BasePresenter

class IssueInfoPresenter(
    view: IssueInfoView,
    provider: IssueInfoProvider
) : BasePresenter<IssueInfoResponseModel>(view, provider) {


}