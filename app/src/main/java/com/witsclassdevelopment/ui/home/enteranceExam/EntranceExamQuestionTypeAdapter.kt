package com.witsclassdevelopment.ui.home.enteranceExam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.witsclassdevelopment.R
import com.github.islamkhsh.CardSliderAdapter
import com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.EnteranceExamQuestionTypeResponse

class EntranceExamQuestionTypeAdapter(var mContext: IEntranceExamQuestionTypeListener) :
    CardSliderAdapter<EntranceExamQuestionTypeAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<EnteranceExamQuestionTypeResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 10
    }
    public fun updateList(list: ArrayList<EnteranceExamQuestionTypeResponse.Datum>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_enterance_exam_question_type_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.textId.text = mList?.get(position)?.name
        holder.textView104.text = mList?.get(position)?.description
        holder.container.setOnClickListener {
            mList?.get(position)?.let { it1 -> mContext.clickEvent(it1) }
        }
        holder.previousSubmissions?.setOnClickListener {
            mContext.launchReport()
        }

        holder?.answerKey?.setOnClickListener {
            mContext.launchAnswerKey(mList?.get(position))
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textId: TextView = view.findViewById(R.id.textId)
        var textView104: TextView = view.findViewById(R.id.textView104)
        var container: CardView = view.findViewById(R.id.container)
        var previousSubmissions: TextView = view.findViewById(R.id.textView105)
        var answerKey: TextView = view.findViewById(R.id.textView106)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}