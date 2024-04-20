package com.witsclassdevelopment.ui.home.previousYearQuestionPaper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.google.android.material.card.MaterialCardView
import com.witsclassdevelopment.R
import com.witsclassdevelopment.service.respose.homeBanner.previousYearQuestionPapers.PreviousYearQuestionResponse

class PreviousYearQuestionPapersAdapter(var mContext:IPreviousYearQuestionPapers) :
    CardSliderAdapter<PreviousYearQuestionPapersAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<PreviousYearQuestionResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 5
    }
    public fun updateList(list: ArrayList<PreviousYearQuestionResponse.Datum>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_previous_year_question_papers_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.title.text = mList?.get(position)?.name
        //Picasso.get().load(mList?.get(position)?.images).into(holder.imageView)
        holder.container.setOnClickListener {
            mList?.get(position)?.let { it1 -> mContext.clickEvent(it1) }
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
        var container: MaterialCardView = view.findViewById(R.id.rootView)
        //var imageView: ImageView = view.findViewById(R.id.imageView19)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}