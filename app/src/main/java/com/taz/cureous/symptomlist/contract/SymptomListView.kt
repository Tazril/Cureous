package com.taz.cureous.symptomlist.contract

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.appcompat.view.ActionMode
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.google.android.material.chip.Chip
import com.taz.cureous.R
import com.taz.cureous.globalmodels.Symptom
import com.taz.cureous.helper.Constant
import com.taz.cureous.mvp.BaseActivity
import com.taz.cureous.mvp.BaseListFragment
import com.taz.cureous.symptomlist.model.SymptomListProvider
import com.taz.cureous.symptomlist.model.SymptomListResponseModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_name_id_list.*

class SymptomListView() : BaseListFragment<SymptomListResponseModel,Symptom,SymptomListAdapter>() {

    override lateinit var recyclerView: RecyclerView
    override val adapter = SymptomListAdapter()

    override fun loadResponse(responseModel: SymptomListResponseModel) {
        list = responseModel.symptomsList!!
        adapter.list = responseModel.symptomsList
        adapter.notifyDataSetChanged()
    }

    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var drawerLayout: DrawerLayout
    lateinit var searchView: SearchView

    override val layoutId: Int = R.layout.fragment_name_id_list
    lateinit var presenter: SymptomListPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = recyclerViewNameIdListFragment
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SymptomListPresenter(this, SymptomListProvider())
        presenter.initPresenter()
        swipeRefreshLayout = baseActivity.swipeRefreshLayout!!
        drawerLayout = activity!!.drawerLayoutMain
        searchView = activity!!.searchViewNameIdList
        searchView.hasListener{ o, s->o.Name.contains(s)}
        adapter hasNoDataDisplay (noDataImageNameIdRv)
        adapter.listener = adapterListener
        adapter.listenerlong = adapterListenerLong
    }

    override fun initView() {
        presenter.getPresenterResponse()
    }


    private val chipViewBySymptomId = hashMapOf<Int, View>()

    fun checkView(itemView: View, chipView: View, symptom: Symptom, set: Boolean = true) {
        if (set) {
            count++
            itemView.setBackgroundColor(Color.GRAY)
            symptom.isSelected = true
            activity!!.chipGroup.addView(chipView)
            chipViewBySymptomId[symptom.ID] = chipView
        } else {
            count--
            itemView.setBackgroundColor(Color.WHITE)
            symptom.isSelected = false
            activity!!.chipGroup.removeView(chipView)
            chipViewBySymptomId.remove(symptom.ID)

        }
        actionMode?.setTitle("$count item(s) Selected")
    }

    private val adapterListener: (View, Symptom) -> Unit = { view, symptom ->
        if (actionMode != null) {
            if (!symptom.isSelected)
                addChip(view, symptom)
            else
                checkView(view, chipViewBySymptomId[symptom.ID]!!, symptom, false)
        } else show("Long press to select")
    }


    private val adapterListenerLong: (Symptom) -> Boolean = {
        if (actionMode == null)
            actionMode = (activity as BaseActivity).startSupportActionMode(callback)
        false
    }

    private fun addChip(itemView: View, symptom: Symptom) {
        val chip = Chip(activity!!)
        chip.text = symptom.Name
        chip.chipIcon = ContextCompat.getDrawable(activity!!, R.drawable.ic_colorize)
        checkView(itemView, chip, symptom)

        chip.isCloseIconVisible = true
        chip.setOnCloseIconClickListener {
            checkView(itemView, it, symptom, false)
        }
    }

    var actionMode: ActionMode? = null
    var count: Int = 0


    val callback = object : ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            if (item?.itemId == R.id.actionDone) {
                loadNext()
                mode?.finish()
                return true
            } else {
                if (drawerLayout.isDrawerOpen(GravityCompat.END))
                    drawerLayout.closeDrawer(GravityCompat.END)
                else
                    drawerLayout.openDrawer(GravityCompat.END)
                return false
            }

        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater!!.inflate(R.menu.menu_contextual, menu)
            swipeRefreshLayout.isEnabled = false
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            actionMode = null; count = 0
            list.forEach { it.isSelected = false }
            adapter.notifyDataSetChanged()
            chipViewBySymptomId.clear(); activity!!.chipGroup.removeAllViews()
            drawerLayout.closeDrawer(GravityCompat.END)
            swipeRefreshLayout.isEnabled = true
            searchView.isIconified = true

        }

    }

    private fun loadNext() {
        val symptomList = list.filter { it.ID in chipViewBySymptomId.keys }
        view!!.findNavController().navigate(
            R.id.action_symptomListView_to_symptomSuggestionListView,
            bundleOf(Constant.SYMPTOM_LIST_KEY to symptomList)
        )
    }


}