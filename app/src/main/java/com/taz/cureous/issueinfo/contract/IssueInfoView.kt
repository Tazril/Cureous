package com.taz.cureous.issueinfo.contract

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import com.google.android.material.chip.Chip
import com.taz.cureous.R
import com.taz.cureous.activities.ResultActivity
import com.taz.cureous.helper.Constant
import com.taz.cureous.helper.Constant.SPECIALISATION_LIST_KEY
import com.taz.cureous.helper.Urls
import com.taz.cureous.issueinfo.model.IssueInfoProvider
import com.taz.cureous.issueinfo.model.IssueInfoResponseModel
import com.taz.cureous.mvp.BaseFragment
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.fragment_issue_info.*

class IssueInfoView() : BaseFragment<IssueInfoResponseModel>() {


    override fun loadResponse(responseModel: IssueInfoResponseModel) {
        fragmentView!!.show()
        nametextViewIssueInfo.text = responseModel.issueinfo!!.name
        descriptiontextViewIssueInfo.text = responseModel.issueinfo.description
        possibleSymptomstextViewIssueInfo.text = responseModel.issueinfo.possibleSymptons
        synonymstextViewIssueInfo.text = responseModel.issueinfo.synonyms
        medicalConditiontextViewIssueInfo.text = responseModel.issueinfo.medicalConditions
        profNametextViewIssueInfo.text = responseModel.issueinfo.profName
        treatmentDescriptiontextViewIssueInfo.text = responseModel.issueinfo.treatmentDescription

        val specialisationList = arguments?.getStringArrayList(SPECIALISATION_LIST_KEY)
        if (specialisationList != null) {
            specialisationListContainer.show()
            specialisationList.forEach {  chipGroupSpecialisationList.addView(newChip(it))}
        }


    }

    fun newChip(name: String): Chip {
        val chip = Chip(activity!!)
        chip.text = name
        chip.isCheckable = false
        chip.chipIcon = ContextCompat.getDrawable(activity!!, R.drawable.ic_colorize)
        chip.isCloseIconVisible = false
        return chip
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = super.onCreateView(inflater, container, savedInstanceState)
        fragmentView!!.hide()
        return v
    }

    override val layoutId: Int = R.layout.fragment_issue_info
    lateinit var presenter: IssueInfoPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val issueId = arguments!!.getInt(Constant.ID_KEY)
        presenter = IssueInfoPresenter(this, IssueInfoProvider(issueId))
        presenter.initPresenter()
    }


    override fun initView() {
        if (activity is ResultActivity)
            (activity as ResultActivity).bottomNavResult.hide()
        presenter.getPresenterResponse()
    }


}