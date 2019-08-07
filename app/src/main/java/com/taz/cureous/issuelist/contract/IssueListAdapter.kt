package com.taz.cureous.issuelist.contract

import android.view.animation.AnimationUtils
import com.taz.cureous.R
import com.taz.cureous.globalmodels.Issue
import com.taz.cureous.mvp.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.item_rv_fragment_name_id_list.view.*

class IssueListAdapter() : BaseRecyclerAdapter<Issue>(R.layout.item_rv_fragment_name_id_list) {
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.itemView.nametextViewItemNameIdList.text = list[position].name
        holder.itemView.cardContainerNameId.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.fade_slide_in_left)
    }

}
