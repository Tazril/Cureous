package com.taz.cureous.mvp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T>(val layoutId: Int) :
    RecyclerView.Adapter<BaseRecyclerAdapter<T>.RecyclerViewHolder>() {

    var list: List<T> = emptyList()
    var listener: ((View, T) -> Unit)? = null
    var listenerlong: ((T) -> Boolean)? = null
    var listenerPos: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int = position

    open inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.invoke(it, list[position])
                    listenerPos?.invoke(position)
                }
            }

            itemView.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION)
                    listenerlong?.invoke(list[position]) ?: false
                else
                    false
            }
        }
    }

    abstract override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int)
}