package com.witsclassdevelopment.ui.chapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.witsclassdevelopment.R
import com.witsclassdevelopment.service.respose.ChapterResponse
import com.github.islamkhsh.CardSliderAdapter

class ChaptersAdapter(var mContext:IChapter) :
    CardSliderAdapter<ChaptersAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<ChapterResponse.ChapterData> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<ChapterResponse.ChapterData>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_chapter, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.chapter.text = "Chapter ${position+1} : "+ (mList?.get(position)?.chapterName ?: "")
        holder.rootView.setOnClickListener {
            mList?.get(position)?.let { it1 -> mContext.clickEvent(it1) }
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var chapter: TextView = view.findViewById(R.id.chapter)
        var rootView: CardView = view.findViewById(R.id.rootView)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}