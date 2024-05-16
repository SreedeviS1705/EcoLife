package com.welkinwits.ui.home.scholarship

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.welkinwits.R
import com.welkinwits.adapter.SingleQuestionOptionListingAdapter
import com.welkinwits.service.respose.homeBanner.scholarship.ScholarshipExamsResponse

class ScholarshipExamsListAdapter(var mContext: Context, var mClickEvent: IScholarshipClickEvent) :
    CardSliderAdapter<ScholarshipExamsListAdapter.FuturePlusSliderViewHolder>() {
    private var mList: ArrayList<ScholarshipExamsResponse.Datum> ?= null
    private var mAdapter: SingleQuestionOptionListingAdapter? = null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 10
    }
    public fun updateList(list: ArrayList<ScholarshipExamsResponse.Datum>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuturePlusSliderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_scholarship_exams_list_single_item, parent, false)
        return FuturePlusSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FuturePlusSliderViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val item = mList?.get(holder.adapterPosition)

        holder.participateScholarShipBtn.setOnClickListener {
            Log.d("rootView", "setOnClickListener: ")
            mClickEvent.clickEvent(item)
        }

        holder.policyLink.setOnClickListener {
            Log.d("policyLink", "policyLink: ")
            mClickEvent.clickPolicy(item)
        }

    }

    class FuturePlusSliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var titile: TextView = view.findViewById(R.id.textView60)
        var examAttendedStatusLabel: TextView = view.findViewById(R.id.examAttendedStatusLabel)
        var participateScholarShipBtn: Button = view.findViewById(R.id.participateScholarShipBtn)
        var policyLink: TextView = view.findViewById(R.id.policyLink)
    }

    override fun bindVH(holder: FuturePlusSliderViewHolder, position: Int){
        val item = mList?.get(position)
        holder.titile.text = item?.name
        if(item?.enable.equals("0")) {
            holder.examAttendedStatusLabel.visibility = View.VISIBLE
            holder.participateScholarShipBtn.visibility = View.GONE
        } else {
            holder.examAttendedStatusLabel.visibility = View.GONE
            holder.participateScholarShipBtn.visibility = View.VISIBLE
        }

        holder.itemView.run {
            /*Picasso.get()
                .load(item)
                .resize(200, 800)
                .centerInside()
                .into(holder.imageSlider)*/
            /*holder.createdOn.text = "Quiz Created on "+item?.addedOn
            holder.name.text = item?.name
            holder.rootView.setOnClickListener {
                mClickEvent.clickEvent(item)
            }*/
        }
    }
}