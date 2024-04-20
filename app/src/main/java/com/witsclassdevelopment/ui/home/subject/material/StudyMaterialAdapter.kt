package com.witsclassdevelopment.ui.home.subject.material

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.witsclassdevelopment.R
import com.witsclassdevelopment.service.respose.MaterialResponse
import com.github.islamkhsh.CardSliderAdapter
import com.google.android.material.card.MaterialCardView

class StudyMaterialAdapter(var mContext: IStudyMaterialListener) :
    CardSliderAdapter<StudyMaterialAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<MaterialResponse.Data> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<MaterialResponse.Data>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_material, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.title.text = mList?.get(position)?.title
        holder.rootView.setOnClickListener {
            mContext.clickEvent(mList!![position])
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
        var rootView:MaterialCardView = view.findViewById(R.id.rootView)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}