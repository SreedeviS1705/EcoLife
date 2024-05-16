package com.welkinwits.ui.home.jobupdates.jobUpdateDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.welkinwits.R
import com.github.islamkhsh.CardSliderAdapter

class JobUpdateDetailsAdapter() :
    CardSliderAdapter<JobUpdateDetailsAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<String> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<String>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_job_update_details_single_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.content.text = mList?.get(position)

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var content: TextView = view.findViewById(R.id.content)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}