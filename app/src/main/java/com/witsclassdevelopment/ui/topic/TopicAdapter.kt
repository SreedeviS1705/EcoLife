package com.witsclassdevelopment.ui.topic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.witsclassdevelopment.R
import com.witsclassdevelopment.service.respose.TopicResponse
import com.github.islamkhsh.CardSliderAdapter

class TopicAdapter(var mContext:ITopic) :
    CardSliderAdapter<TopicAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<TopicResponse.TopicData> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<TopicResponse.TopicData>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_topic_sigle_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val item = mList?.get(position)
        holder.topicTextId.text = item?.topicName
        holder.topicContainer.setOnClickListener {
            mContext.clickEvent(item)
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var topicTextId: TextView = view.findViewById(R.id.topicTextId)
        var topicContainer: CardView = view.findViewById(R.id.topicContainer)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}