package com.witsclassdevelopment.ui.home.enteranceExam.report

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.witsclassdevelopment.R
import com.github.islamkhsh.CardSliderAdapter
import com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.report.EnteranceExamReportsResponse

class EntranceReportAdapter(var mContext:Context, var mClickContext:IEntranceExamReportsListener) :
    CardSliderAdapter<EntranceReportAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<EnteranceExamReportsResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 10
    }
    public fun updateList(list: ArrayList<EnteranceExamReportsResponse.Datum>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_enterance_exam_reports_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        var item = mList?.get(position)
        holder.name.text = item?.name
        holder.addedOn.text = item?.addedOn
        holder.mark.text = "Score: "+item?.marks+" out of "+item?.grossMarks
        if(item?.examStatus.equals("passed")) {
            holder.result.setTextColor(ContextCompat.getColor(mContext, R.color.light_green))
        } else {
            holder.result.setTextColor(ContextCompat.getColor(mContext, R.color.fail))
        }
        holder.result.text = mList?.get(position)?.examStatus
        holder.container.setOnClickListener {
            mList?.get(position)?.let { it1 -> mClickContext.clickEvent(it1) }
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.textId)
        var addedOn: TextView = view.findViewById(R.id.textView104)
        var result: TextView = view.findViewById(R.id.resultStatus)
        var mark: TextView = view.findViewById(R.id.textView106)
        var container: CardView = view.findViewById(R.id.container)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}