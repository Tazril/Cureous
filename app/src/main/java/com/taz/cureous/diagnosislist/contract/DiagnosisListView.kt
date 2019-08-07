package com.taz.cureous.diagnosislist.contract

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.taz.cureous.R
import com.taz.cureous.activities.ResultActivity
import com.taz.cureous.diagnosislist.model.DiagnosisListProvider
import com.taz.cureous.diagnosislist.model.DiagnosisListResponseModel
import com.taz.cureous.globalmodels.Issue
import com.taz.cureous.helper.Constant
import com.taz.cureous.helper.Urls
import com.taz.cureous.mvp.BaseFragment
import com.taz.cureous.mvp.BaseListFragment
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.fragment_name_id_list.*

class DiagnosisListView() : BaseListFragment<DiagnosisListResponseModel,Issue,DiagnosisListAdapter>() {

    override val adapter: DiagnosisListAdapter = DiagnosisListAdapter()
    override lateinit var recyclerView: RecyclerView

    override fun loadResponse(responseModel: DiagnosisListResponseModel) {
        adapter.list = responseModel.issuesSpecsList!!.map { it.Issue }
        adapter.notifyDataSetChanged()
        adapter.listenerPos = {
            navController.navigate(
                R.id.action_diagnosisListView_to_issueInfoView,
                bundleOf(
                    Constant.ID_KEY to responseModel.issuesSpecsList[it].Issue.id,
                    Constant.SPECIALISATION_LIST_KEY to responseModel.issuesSpecsList[it].Specialisation.map { it.Name })
            )
        }
        (activity as ResultActivity).bottomNavResult.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = recyclerViewNameIdListFragment
        super.onViewCreated(view, savedInstanceState)
    }

    override val layoutId: Int = R.layout.fragment_name_id_list
    lateinit var presenter: DiagnosisListPresenter
    lateinit var provider: DiagnosisListProvider
    lateinit var navController: NavController


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        provider = DiagnosisListProvider()
        presenter = DiagnosisListPresenter(this, provider)
        presenter.initPresenter()
        navController = view!!.findNavController()
        searchViewNameIdList.gone()
        blankView.show()
        adapter hasNoDataDisplay(noDataImageNameIdRv)
    }


    override fun initView() {
        provider.sIdList = Gson().toJson((activity as ResultActivity).sIdList)
        provider.gender = (activity as ResultActivity).gender
        provider.yob = (activity as ResultActivity).yob
        presenter.getPresenterResponse()
    }


}