package com.welkinwits.ui.home.feePayment.feePaymentModeOfStudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.welkinwits.R
import com.welkinwits.service.respose.homeBanner.feePayment.FeePaymentModeOfStudyResponse
import com.github.islamkhsh.CardSliderAdapter

class FeePaymentModeOfPaymentAdapter(var mContext: IModeOfPayment) :
    CardSliderAdapter<FeePaymentModeOfPaymentAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<FeePaymentModeOfStudyResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }

    public fun updateList(list: ArrayList<FeePaymentModeOfStudyResponse.Datum>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_mode_of_payment_single_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.label.text = mList?.get(position)?.name
        holder.clickItem.setOnClickListener {
            mContext.clickEvent(mList?.get(position))
        }
        /*holder.jobType.text = mList?.get(position)?.description
        holder.address.text = mList?.get(position)?.place
        holder.container.setOnClickListener {
            mList?.get(position)?.let { it1 -> mContext.clickEvent(it1) }
        }*/

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var label: TextView = view.findViewById(R.id.label)
        var clickItem: ConstraintLayout = view.findViewById(R.id.clickItem)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}