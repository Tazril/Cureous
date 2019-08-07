package com.taz.cureous.symptomsuggestions.contract

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.chip.Chip
import com.google.gson.Gson
import com.taz.cureous.R
import com.taz.cureous.globalmodels.Symptom
import com.taz.cureous.helper.Constant
import com.taz.cureous.helper.Constant.DEF_GENDER
import com.taz.cureous.helper.Constant.DEF_YEAR_OF_BIRTH
import com.taz.cureous.helper.Constant.GENDER_KEY
import com.taz.cureous.helper.Constant.SYMPTOM_LIST_KEY
import com.taz.cureous.helper.Constant.YEAR_OF_BIRTH_KEY
import com.taz.cureous.mvp.BaseFragment
import com.taz.cureous.symptomsuggestions.model.SymptomSuggestionListProvider
import com.taz.cureous.symptomsuggestions.model.SymptomSuggestionResponseModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_suggestions_list.*

class SymptomSuggestionListView() : BaseFragment<SymptomSuggestionResponseModel>() {

    private lateinit var proposedSymptomlist: MutableList<Symptom>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun loadResponse(responseModel: SymptomSuggestionResponseModel) {
        proposedSymptomlist = responseModel.symptomsList!!.toMutableList()
        adapter.apply {
            list = responseModel.symptomsList.toMutableList()
            notifyDataSetChanged()
            listener = adapterListener

        }

    }


    lateinit var selectedSymptomList: MutableList<Symptom>

    override val layoutId: Int = R.layout.fragment_suggestions_list

    lateinit var presenterSuggestion: SymptomSuggestionListPresenter
    lateinit var provider: SymptomSuggestionListProvider


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        baseActivity.swipeRefreshLayout?.isEnabled = false
        fragmentView!!.show()
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
            override fun onChanged() {
                super.onChanged()
                checkEmpty()
            }
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                checkEmpty()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                checkEmpty()
            }
            fun checkEmpty() {
                noDataImageNameIdRv.visibility = if (adapter.itemCount == 0) View.VISIBLE else View.GONE
            }
        })

        fragmentView!!.visibility = View.VISIBLE
        provider = SymptomSuggestionListProvider()
        presenterSuggestion = SymptomSuggestionListPresenter(this, provider)
        presenterSuggestion.initPresenter()

    }

    lateinit var drawerLayout: DrawerLayout
    val adapter = SymptomSuggestionAdapter()
    var gender: String = DEF_GENDER
    var yob: Int = DEF_YEAR_OF_BIRTH

    override fun initView() {
        recyclerViewSuggestionsFragment.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
        }
        recyclerViewSuggestionsFragment.adapter = adapter

        selectedSymptomList = arguments!!.getParcelableArrayList(SYMPTOM_LIST_KEY)

        populateChipGroup()
        radioGroupGender.setOnCheckedChangeListener { _, checkedId ->
            gender = if (checkedId == radioBtnMale.id) "male" else "female"
        }

        btnSuggestions.setOnClickListener {
            yob = ageSuggestionsEditText.text.toString().toInt()
            if (yob > 1900) loadList()
            else showError("Enter proper year of birth")
        }

        drawerLayout = activity!!.drawerLayoutMain

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {       inflater.inflate(R.menu.menu_done, menu)    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionDoneMenu) {
            val bundle = bundleOf(
                GENDER_KEY to gender,
                YEAR_OF_BIRTH_KEY to yob,
                SYMPTOM_LIST_KEY to selectedSymptomList.map { it.ID } )
            view!!.findNavController().navigate(R.id.action_symptomSuggestionListView_to_resultActivity, bundle)
            return true
        }
        else       return super.onOptionsItemSelected(item)
    }

    private fun loadList() {
        provider.gender = gender
        provider.yob = yob
        provider.sIdList = Gson().toJson(selectedSymptomList.map { it.ID })

        presenterSuggestion.getPresenterResponse()
    }

    fun newChip(symptom: Symptom): Chip {
        val chip = Chip(activity!!)
        chip.text = symptom.Name
        chip.chipIcon = ContextCompat.getDrawable(activity!!, R.drawable.ic_colorize)
        chip.isCloseIconVisible = true
        chip.setOnCloseIconClickListener {
            selectedSymptomList.remove(symptom)
            adapter.apply {
                list.add(symptom)
                notifyItemInserted(adapter.itemCount - 1)
            }
            chipGroupSelected.removeView(it)
        }
        return chip
    }

    private fun populateChipGroup() {
        selectedSymptomList.forEach {
            chipGroupSelected.addView(newChip(it))
        }
    }

    private val adapterListener: (Int, Symptom) -> Unit = { position, symptom ->
        adapter.apply {
            list.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, adapter.itemCount)
        }
        selectedSymptomList.add(symptom)
        chipGroupSelected.addView(newChip(symptom))
    }

}