package com.welkinwits.ui.home.classroomCategory.quiz.report

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.welkinwits.R
import com.welkinwits.service.respose.homeBanner.quiz.report.QuizReportsResponse

class QuizReportsAdapter(var mContext: Context, var mClickContext:IQuizReports) :
    CardSliderAdapter<QuizReportsAdapter.FuturePlusSliderViewHolder>() {
    private var mList: ArrayList<QuizReportsResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 10
    }
    public fun updateList(list: ArrayList<QuizReportsResponse.Datum>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuturePlusSliderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_quiz_reports_single_item, parent, false)
        return FuturePlusSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FuturePlusSliderViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        /*val item = sliderList?.get(holder.adapterPosition)
        Picasso.get()
            .load(item?.imageUrl)
            .resize(800, 800)
            .centerInside()
            .into(holder.imageSlider)*/

    }

    class FuturePlusSliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.textView60)
        var createdOn:TextView = view.findViewById(R.id.actionTextId)
        var totalMarks:TextView = view.findViewById(R.id.textView61)
        var resultStatus:TextView = view.findViewById(R.id.resultStatus)
        var attendedOn:TextView = view.findViewById(R.id.textView62)
        var rootView:CardView = view.findViewById(R.id.rootView)
    }

    override fun bindVH(holder: FuturePlusSliderViewHolder, position: Int){
        val item = mList?.get(position)
        holder.name.text = item?.name
        holder.createdOn.text = "Created on "+item?.addedOn
        holder.totalMarks.text = "Total Marks : "+item?.maxMarks
        holder.resultStatus.text = item?.result
        holder.attendedOn.text = "Last attended on : "+item?.attendOn
        if(item?.result.equals("fail")) {
            holder.resultStatus.setTextColor(ContextCompat.getColor(mContext, R.color.fail));
        } else {
            holder.resultStatus.setTextColor(ContextCompat.getColor(mContext, R.color.pass));
        }

        holder.rootView.setOnClickListener {
            mClickContext.clickEvent(item)
        }


        /*
        holder.itemView.run {
            Picasso.get()
                .load(item)
                .resize(200, 800)
                .centerInside()
                .into(holder.imageSlider)
            holder.createdOn.text = "Quiz Created on "+item?.addedOn
            holder.name.text = item?.name
            holder.rootView.setOnClickListener {
                mClickEvent.clickEvent(item)
            }
        }*/
    }
}