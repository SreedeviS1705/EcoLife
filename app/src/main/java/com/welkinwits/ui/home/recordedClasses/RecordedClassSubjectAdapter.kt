package com.welkinwits.ui.home.recordedClasses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.welkinwits.R
import com.welkinwits.service.respose.SubjectResponse
import com.github.islamkhsh.CardSliderAdapter

class RecordedClassSubjectAdapter(var mContext:IRecordedClass) :
    CardSliderAdapter<RecordedClassSubjectAdapter.SubjectViewHolder>() {
     var mList: ArrayList<SubjectResponse.SubjectData> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<SubjectResponse.SubjectData>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_recorded_class_subject_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)


        holder.subjectTitle.text = mList?.get(position)?.subjectName
        holder.subjectContainer.setOnClickListener {
            mList?.get(position)?.let { it1 -> mContext.clickEvent(it1) }
        }

    }

    inner class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var subjectTitle: TextView = view.findViewById(R.id.subjectTextId)
        var subjectContainer: CardView = view.findViewById(R.id.subjectContainer)
        val backgroundLayout: ConstraintLayout = view.findViewById(R.id.backgroundLayout)


    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}