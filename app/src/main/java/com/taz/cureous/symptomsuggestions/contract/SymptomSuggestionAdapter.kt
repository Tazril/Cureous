package com.taz.cureous.symptomsuggestions.contract

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.taz.cureous.R
import com.taz.cureous.globalmodels.Symptom
import kotlinx.android.synthetic.main.fragment_suggestions_list.*
import kotlinx.android.synthetic.main.item_rv_fragment_name_id_list.view.*

class SymptomSuggestionAdapter() : RecyclerView.Adapter<SymptomSuggestionAdapter.RecyclerViewHolder>() {

    var list = mutableListOf<Symptom>()
    var listener: ((Int, Symptom) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_fragment_name_id_list, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int = position

    open inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                val position = layoutPosition
                if (position != RecyclerView.NO_POSITION)
                    listener?.invoke(position, list[position])
            }

        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.itemView.nametextViewItemNameIdList.text = list[position].Name
    }


}
