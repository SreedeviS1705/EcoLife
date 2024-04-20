package com.witsclassdevelopment.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment.feeStructure

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.witsclassdevelopment.R
import com.witsclassdevelopment.service.respose.homeBanner.feeStructure.FeeStructureResponse
import com.github.islamkhsh.CardSliderAdapter

class FeeStructureAdapter(var mContext: IFeeStructureFragment) :
    CardSliderAdapter<FeeStructureAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<FeeStructureResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<FeeStructureResponse.Datum>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_fee_structure_single_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val item = mList!![position]
        holder.title.text = item.packageName
        holder.decription.text = item.description
        holder.amount.paintFlags = holder.amount.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        holder.amount.text = "Amount: "+item.amount
        holder.offerAmount.text = "Offer Amount: "+item.offerAmount
        holder.makePaymentBtn.setOnClickListener {
            mContext.clickEvent(item)
        }

        holder.partPayment.setOnClickListener {
            mContext.clickPartPayment(mList!![position])
        }

        if(item.partpayEnabled.equals("0")) {
            holder.textView36.visibility = View.GONE
            holder.partPayment.visibility = View.GONE
        } else {
            holder.textView36.visibility = View.VISIBLE
            holder.partPayment.visibility = View.VISIBLE
        }

        if(item.enableOption.equals("0")) {
            holder.makePaymentBtn.visibility = View.GONE
            holder.subscribedTextId.visibility = View.VISIBLE
        } else {
            holder.makePaymentBtn.visibility = View.VISIBLE
            holder.subscribedTextId.visibility = View.GONE
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.textView27)
        var decription: TextView = view.findViewById(R.id.textView28)
        var amount: TextView = view.findViewById(R.id.textView29)
        var offerAmount: TextView = view.findViewById(R.id.offerAmount)
        var makePaymentBtn: Button = view.findViewById(R.id.makePaymentBtn)
        var partPayment: TextView = view.findViewById(R.id.textView35)
        var textView36: TextView = view.findViewById(R.id.textView36)
        var subscribedTextId: TextView = view.findViewById(R.id.subscribedTextId)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}