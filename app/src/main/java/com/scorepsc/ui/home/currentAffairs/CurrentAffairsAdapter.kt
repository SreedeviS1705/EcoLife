package com.scorepsc.ui.home.currentAffairs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.scorepsc.R
import com.github.islamkhsh.CardSliderAdapter
import com.scorepsc.service.respose.homeBanner.currentAfairs.CurrentAfairsResponse

class CurrentAffairsAdapter(var mClickContext:ICurrentAffairs) :
    CardSliderAdapter<CurrentAffairsAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<CurrentAfairsResponse.Data> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<CurrentAfairsResponse.Data>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_current_affairs_sigle_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val item = mList?.get(position)
        holder.heading.text = item?.name
        holder.container.setOnClickListener {
            mClickContext.clickEvent(item)
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var heading: TextView = view.findViewById(R.id.heading)
        var container: CardView = view.findViewById(R.id.container)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}