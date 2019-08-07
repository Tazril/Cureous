package com.taz.cureous.specialisationlist.contract

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.taz.cureous.R
import com.taz.cureous.activities.ResultActivity
import com.taz.cureous.globalmodels.Specialisation
import com.taz.cureous.mvp.BaseFragment
import com.taz.cureous.mvp.BaseListFragment
import com.taz.cureous.specialisationlist.model.SpecialisationListProvider
import com.taz.cureous.specialisationlist.model.SpecialisationListResponseModel
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.fragment_name_id_list.*

class SpecialisationListView() : BaseListFragment<SpecialisationListResponseModel,Specialisation,SpecialisationListAdapter>() {
    override val adapter: SpecialisationListAdapter = SpecialisationListAdapter()
    override lateinit var recyclerView: RecyclerView


    override fun loadResponse(responseModel: SpecialisationListResponseModel) {
        adapter.list = responseModel.specialisationList!!
        adapter.notifyDataSetChanged()
        (activity as ResultActivity).bottomNavResult.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = recyclerViewNameIdListFragment
        super.onViewCreated(view, savedInstanceState)
    }

    override val layoutId: Int = R.layout.fragment_name_id_list
    lateinit var presenter: SpecialisationListPresenter
    lateinit var provider: SpecialisationListProvider


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        blankView.show()
        searchViewNameIdList.gone()
        adapter hasNoDataDisplay(noDataImageNameIdRv)
        provider = SpecialisationListProvider()
        presenter = SpecialisationListPresenter(this, provider)
        presenter.initPresenter()
    }

    override fun initView() {
        provider.sIdList = Gson().toJson((activity as ResultActivity).sIdList)
        provider.gender = (activity as ResultActivity).gender
        provider.yob = (activity as ResultActivity).yob
        presenter.getPresenterResponse()

    }
}