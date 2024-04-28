package com.scorepsc.ui.home.newsevents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.scorepsc.R
import com.scorepsc.service.respose.NewsResponse
import com.github.islamkhsh.CardSliderAdapter
import com.squareup.picasso.Picasso

class NewsEventsAdapter(var mContext:INewsListener) :
    CardSliderAdapter<NewsEventsAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<NewsResponse.NewsData> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<NewsResponse.NewsData>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_news_events_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.title.text = mList?.get(position)?.heading
        Picasso.get().load(mList?.get(position)?.images)?.placeholder(R.drawable.gallery_placeholder)?.into(holder.imageView)
        holder.container.setOnClickListener {
            mList?.get(position)?.let { it1 -> mContext.clickEvent(it1) }
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.subjectTextId)
        var container: CardView = view.findViewById(R.id.subjectContainer)
        var imageView: ImageView = view.findViewById(R.id.imageView19)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}