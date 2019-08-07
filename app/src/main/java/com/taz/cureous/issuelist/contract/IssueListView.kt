package com.taz.cureous.issuelist.contract

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.taz.cureous.R
import com.taz.cureous.globalmodels.Issue
import com.taz.cureous.helper.Constant
import com.taz.cureous.issuelist.model.IssueListProvider
import com.taz.cureous.issuelist.model.IssueListResponseModel
import com.taz.cureous.mvp.BaseListFragment
import kotlinx.android.synthetic.main.fragment_name_id_list.*
import kotlinx.android.synthetic.main.fragment_name_id_list.view.*

class IssueListView() : BaseListFragment<IssueListResponseModel, Issue, IssueListAdapter>() {

    override val adapter: IssueListAdapter = IssueListAdapter()
    override lateinit var recyclerView: RecyclerView


    private var issueList = emptyList<Issue>()

    override fun loadResponse(responseModel: IssueListResponseModel) {
        adapter.list = responseModel.issuesList!!
        issueList = adapter.list
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = recyclerViewNameIdListFragment
        super.onViewCreated(view, savedInstanceState)
    }

    override val layoutId: Int = R.layout.fragment_name_id_list
    lateinit var presenter: IssueListPresenter
    lateinit var navController: NavController

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = IssueListPresenter(this, IssueListProvider())
        presenter.initPresenter()
        navController = view!!.findNavController()
        val searchView = view!!.searchViewNameIdList
        searchView.hasListener { o, s -> o.name.contains(s) }
        adapter hasNoDataDisplay (noDataImageNameIdRv)
        adapter.listener = { _, it ->
            hideKeyboard()
            navController.navigate(
                R.id.action_issueListView_to_issueInfoView,
                bundleOf(Constant.ID_KEY to it.id)
            )
        }
    }

    override fun initView() {
        presenter.getPresenterResponse()
    }

}