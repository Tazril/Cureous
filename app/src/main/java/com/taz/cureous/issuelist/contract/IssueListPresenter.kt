package com.taz.cureous.issuelist.contract

import com.taz.cureous.issuelist.model.IssueListProvider
import com.taz.cureous.issuelist.model.IssueListResponseModel
import com.taz.cureous.mvp.BasePresenter

class IssueListPresenter(
    view: IssueListView,
    provider: IssueListProvider
) : BasePresenter<IssueListResponseModel>(view, provider) {


}