package com.taz.cureous.mvp

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListFragment<RESPONSEMODEL, OBJECT, ADAPTER : BaseRecyclerAdapter<OBJECT>>() :
    BaseFragment<RESPONSEMODEL>() {

    abstract val adapter: ADAPTER
    abstract var recyclerView: RecyclerView

    var list: List<OBJECT> = emptyList()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
        }
        recyclerView.adapter = adapter

    }

    fun SearchView.hasListener(predicate: (OBJECT, String) -> (Boolean)) {
        this.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.list = list.filter { predicate(it, newText!!) }
                    adapter.notifyDataSetChanged()
                    return true
                }
            })
    }
    infix fun ADAPTER.hasNoDataDisplay(noDataView:View){
        this.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
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
                noDataView.visibility = (if (adapter.itemCount == 0) View.VISIBLE else View.GONE)
            }
        } )
    }


}