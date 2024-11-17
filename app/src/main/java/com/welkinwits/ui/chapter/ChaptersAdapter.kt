package com.welkinwits.ui.chapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.welkinwits.R
import com.welkinwits.service.respose.ChapterResponse
import com.github.islamkhsh.CardSliderAdapter

class ChaptersAdapter(var mContext: IChapter,val context: Context?,var redirectType: String?) :
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
        if(redirectType.equals("recordedClasses")) {
            holder.rootView.setCardBackgroundColor(ContextCompat.getColor(context!!,R.color.white))
            holder.chapterIcon.visibility = View.VISIBLE
            holder.chapter.setTextColor(ContextCompat.getColor(context,R.color.black))
            holder.chapterArrow.visibility = View.GONE
        }else{
            holder.rootView.setCardBackgroundColor(ContextCompat.getColor(context!!,R.color.psc_blue))
            holder.chapterIcon.visibility = View.VISIBLE
            holder.chapter.setTextColor(ContextCompat.getColor(context,R.color.white))
            holder.chapterArrow.setColorFilter(R.color.white)
        }
        holder.chapter.text = mList?.get(position)?.chapterName ?: ""
        holder.rootView.setOnClickListener {
            mList?.get(position)?.let { it1 -> mContext.clickEvent(it1) }
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var chapter: TextView = view.findViewById(R.id.chapter)
        var chapterIcon: ImageView = view.findViewById(R.id.chapterIcon)
        var chapterArrow: ImageView = view.findViewById(R.id.chapterArrow)
        var rootView: CardView = view.findViewById(R.id.rootView)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}