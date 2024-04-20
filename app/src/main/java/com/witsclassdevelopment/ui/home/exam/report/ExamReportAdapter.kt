package com.witsclassdevelopment.ui.home.exam.report

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.witsclassdevelopment.R
import com.witsclassdevelopment.service.respose.homeBanner.exam.report.ExamReportResponse

class ExamReportAdapter(var mContext:Context, var mClick:IExamReportsListener) :
    CardSliderAdapter<ExamReportAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<ExamReportResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<ExamReportResponse.Datum>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_exam_report_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.title.text = mList?.get(position)?.name
        holder.date.text = "Attended on "+mList?.get(position)?.attentedOn
        holder.score.text = "Score: "+mList?.get(position)?.marks + " out of "+mList?.get(position)?.max_marks
        holder.resultStatus.text = "Result: "+mList?.get(position)?.result
        if(mList?.get(position)?.result.equals("passed")) {
            holder.resultStatus.setTextColor(ContextCompat.getColor(mContext, R.color.pass))
        } else {
            holder.resultStatus.setTextColor(ContextCompat.getColor(mContext, R.color.fail))
        }

        holder.container.setOnClickListener {
            mList?.get(position)?.let { it1 -> mClick.clickEvent(it1) }
        }
    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
        var date: TextView = view.findViewById(R.id.date)
        var resultStatus: TextView = view.findViewById(R.id.resultStatus)
        var score: TextView = view.findViewById(R.id.score)
        var container: CardView = view.findViewById(R.id.container)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}