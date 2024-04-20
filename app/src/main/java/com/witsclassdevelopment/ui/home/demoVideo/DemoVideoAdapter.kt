package com.witsclassdevelopment.ui.home.demoVideo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.witsclassdevelopment.R
import com.witsclassdevelopment.service.respose.homeBanner.demoVideos.DemoVideosResponse
import com.github.islamkhsh.CardSliderAdapter

class DemoVideoAdapter(var mContext:IDemoVideo) :
    CardSliderAdapter<DemoVideoAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<DemoVideosResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<DemoVideosResponse.Datum>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_demo_videos_sigle_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val item = mList?.get(position)
        holder.titleId.text = item?.title
        holder.container.setOnClickListener {
            mContext.clickEvent(item)
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var titleId: TextView = view.findViewById(R.id.titleId)
        var container: CardView = view.findViewById(R.id.container)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}