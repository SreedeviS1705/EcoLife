package com.welkinwits.ui.home.subject.batch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.welkinwits.R
import com.welkinwits.service.respose.homeBanner.liveClass.batch.LiveClassBatchResponse
import com.github.islamkhsh.CardSliderAdapter

class BatchAdapter(var mContext: IBatchListener) :
    CardSliderAdapter<BatchAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<LiveClassBatchResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<LiveClassBatchResponse.Datum>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_batch_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.subjectTitle.text = mList?.get(position)?.batchName
        holder.tutorNameId.text = mList?.get(position)?.tutorName
        holder.subjectContainer.setOnClickListener {
            mContext.clickEvent(mList!![position])
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var subjectTitle:TextView = view.findViewById(R.id.subjectTextId)
        var tutorNameId:TextView = view.findViewById(R.id.tutorNameId)
        var subjectContainer:CardView = view.findViewById(R.id.subjectContainer)
        val backgroundLayout: ConstraintLayout = view.findViewById(R.id.backgroundLayout)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}