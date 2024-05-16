package com.welkinwits.ui.home.subject.batch.liveClasses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.welkinwits.R
import com.welkinwits.service.respose.homeBanner.liveClass.liveclasses.LiveClassesResponse
import com.github.islamkhsh.CardSliderAdapter


class ClassesAdapter(var activity: FragmentActivity?) :
    CardSliderAdapter<ClassesAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<LiveClassesResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<LiveClassesResponse.Datum>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_classes_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.subjectTitle.text = mList?.get(position)?.batchName
        holder.tutorNameId.text = mList?.get(position)?.tutorName

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var subjectTitle:TextView = view.findViewById(R.id.subjectTextId)
        var tutorNameId:TextView = view.findViewById(R.id.tutorNameId)
        var subjectContainer:CardView = view.findViewById(R.id.subjectContainer)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}