package com.welkinwits.ui.home.subject.demoClass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.welkinwits.R
import com.welkinwits.service.respose.homeBanner.demoClassSubject.DemoClassWithSubjectResponse

class DemoClassSubjectAdapter(var mContext: IDemoClassSubjectListener) :
    CardSliderAdapter<DemoClassSubjectAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<DemoClassWithSubjectResponse.HomeBannerData> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<DemoClassWithSubjectResponse.HomeBannerData>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_demo_class_subject_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.demoClassTextId.text = mList?.get(position)?.title
        holder.demoClassSubjectContainer.setOnClickListener {
            mList?.get(position)?.let { it1 -> mContext.clickEvent(it1) }
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var demoClassTextId:TextView = view.findViewById(R.id.demoClassTextId)
        var demoClassSubjectContainer:CardView = view.findViewById(R.id.demoClassSubjectContainer)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}