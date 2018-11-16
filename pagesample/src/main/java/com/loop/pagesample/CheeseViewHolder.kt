package com.loop.pagesample

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CheeseViewHolder(parent:ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_cheese,parent,false)
){
    private val nameView=itemView.findViewById<TextView>(R.id.name)

    var cheese:Cheese?=null

    /**
     *  Item may be null if they are not paged in yet.
     *  PageListAdapter will re-bind the viewHolder when item is loaded
     */
    fun bind(cheese: Cheese?){
        this.cheese=cheese
        nameView.text=cheese?.name
    }

}
