package com.taz.cureous.symptomlist.contract

import android.graphics.Color
import android.view.animation.AnimationUtils
import com.taz.cureous.R
import com.taz.cureous.globalmodels.Symptom
import com.taz.cureous.mvp.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.item_rv_fragment_name_id_list.view.*

class SymptomListAdapter() : BaseRecyclerAdapter<Symptom>(R.layout.item_rv_fragment_name_id_list) {
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.itemView.nametextViewItemNameIdList.text = list[position].Name
        holder.itemView.cardContainerNameId.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.fade_slide_in_left)

        if (list[position].isSelected)
            holder.itemView.setBackgroundColor(Color.GRAY)
        else
            holder.itemView.setBackgroundColor(Color.WHITE)
    }

}
