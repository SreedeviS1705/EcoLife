package com.witsclassdevelopment.ui.home.accadamicStreamSwitch

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.witsclassdevelopment.R
import com.witsclassdevelopment.service.respose.homeBanner.acadamicStreamSwitch.acadamicLevels.GetAcadamicLevelsResponse

class AcademicLevelsAdapter(var context:IAcademicStreamSwitch) :
    CardSliderAdapter<AcademicLevelsAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<GetAcadamicLevelsResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<GetAcadamicLevelsResponse.Datum>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_academic_levels_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemTextId.text = mList?.get(position)?.levels
        if(mList?.get(position)?.isCurrent == 1) {
            holder.currentStreamTextId.visibility = View.VISIBLE
        } else {
            holder.currentStreamTextId.visibility = View.GONE
        }
        holder.academicLevelContainer.setOnClickListener {
            mList?.get(position)?.let { it1 -> context.clickEvent(it1) }
            for (item in mList!!) {
                item.isSelected = false
            }
            mList?.get(position)?.isSelected = true
            notifyDataSetChanged()
        }

        if(mList?.get(position)?.isSelected == true) {
            holder.academicLevelContainer.setCardBackgroundColor(Color.rgb(205, 250, 202))
        } else {
            holder.academicLevelContainer.setCardBackgroundColor(Color.WHITE)
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextId: TextView = view.findViewById(R.id.itemTextId)
        var currentStreamTextId: ImageView = view.findViewById(R.id.currentStreamTextId)
        var academicLevelContainer: CardView = view.findViewById(R.id.academicLevelContainer)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}