package com.scorepsc.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment.feeStructure.partPayment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.scorepsc.R
import com.scorepsc.service.respose.homeBanner.partPayment.PartPaymentListResponse
import com.github.islamkhsh.CardSliderAdapter

class PartPaymentListAdapter(var mContext:IPartPaymentFragment) :
    CardSliderAdapter<PartPaymentListAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<PartPaymentListResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<PartPaymentListResponse.Datum>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_part_payment_single_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val item = mList!![position]
        holder.title.text = item.title
        holder.packageTime.text = item.description
        holder.amount.text = item.amount

        if(item.partPaymentStatus.equals("active")) {
            holder.makePaymentBtn.visibility = View.VISIBLE
            holder.partPaymentCompletionText.visibility = View.GONE
            holder.containerId.setCardBackgroundColor(Color.WHITE)
        } else if(item.partPaymentStatus.equals("completed")) {
            holder.makePaymentBtn.visibility = View.GONE
            holder.partPaymentCompletionText.visibility = View.VISIBLE
            holder.partPaymentCompletionText.setTextColor(Color.parseColor("#4CAF50"))
            holder.partPaymentCompletionText.text = "You have made payment on "+item.paymentDate
        } else {
            holder.makePaymentBtn.visibility = View.VISIBLE
            holder.partPaymentCompletionText.visibility = View.GONE
            holder.containerId.setCardBackgroundColor(Color.LTGRAY)
        }

        holder.makePaymentBtn.setOnClickListener {
            if(item.partPaymentStatus.equals("active")) {
                mContext.clickEvent(item)
            }
        }
    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.textView28)
        var packageTime: TextView = view.findViewById(R.id.textView29)
        var amount: TextView = view.findViewById(R.id.offerAmount)
        var containerId: CardView = view.findViewById(R.id.containerId)
        var makePaymentBtn: Button = view.findViewById(R.id.makePaymentBtn)
        var partPaymentCompletionText: TextView = view.findViewById(R.id.partPaymentCompletionText)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}