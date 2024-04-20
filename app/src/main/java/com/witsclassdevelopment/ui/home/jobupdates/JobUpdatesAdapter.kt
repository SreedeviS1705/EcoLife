package com.witsclassdevelopment.ui.home.jobupdates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.witsclassdevelopment.R
import com.witsclassdevelopment.service.respose.homeBanner.jobupdates.JobUpdatesResponse
import com.github.islamkhsh.CardSliderAdapter

class JobUpdatesAdapter(var mContext:IJobUpdates) :
    CardSliderAdapter<JobUpdatesAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<JobUpdatesResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<JobUpdatesResponse.Datum>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_job_updates_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.jobTitle.text = mList?.get(position)?.title
        holder.jobType.text = mList?.get(position)?.description
        holder.address.text = mList?.get(position)?.place
        holder.container.setOnClickListener {
            mList?.get(position)?.let { it1 -> mContext.clickEvent(it1) }
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var jobTitle: TextView = view.findViewById(R.id.jobTitle)
        var jobType: TextView = view.findViewById(R.id.jobType)
        var address: TextView = view.findViewById(R.id.address)
        var container: CardView = view.findViewById(R.id.container)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}