package com.witsclassdevelopment.ui.home.feePayment.paymentHistory

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.witsclassdevelopment.R
import com.witsclassdevelopment.service.respose.homeBanner.paymentHistory.GetPaymentHistoryResponse
import com.github.islamkhsh.CardSliderAdapter

class PaymentHistoryAdapter() :
    CardSliderAdapter<PaymentHistoryAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<GetPaymentHistoryResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<GetPaymentHistoryResponse.Datum>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_single_payment_history, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.index.text = ""+(position+1)
        if(mList?.get(position)?.orderStatus.equals("Completed")) {
            holder.status.text = Html.fromHtml("<b>Status: </b>"+"<font color=#0A9B07>"+mList?.get(position)?.orderStatus+"</font>")

        } else if(mList?.get(position)?.orderStatus.equals("Pending")) {
            holder.status.text = Html.fromHtml("<b>Status: </b>"+"<font color=#FB6322>"+mList?.get(position)?.orderStatus+"</font>")

        } else if(mList?.get(position)?.orderStatus.equals("Failed")) {
            holder.status.text = Html.fromHtml("<b>Status: </b>"+"<font color=#E80000>"+mList?.get(position)?.orderStatus+"</font>")

        }
        holder.paymentDate.text = Html.fromHtml("<b>Made on </b>"+mList?.get(position)?.addedOn)
        holder.orderID.text = Html.fromHtml("<b>Order ID : #</b>"+mList?.get(position)?.orderId)
        holder.transactionId.text = Html.fromHtml("<b>Transaction ID : </b>"+mList?.get(position)?.vendorTransId)
        holder.amount.text = Html.fromHtml("<b>Amount : </b>"+mList?.get(position)?.orderAmount)
        if(mList?.get(position)?.vendorPaymentMethodType.equals("upi") || mList?.get(position)?.vendorPaymentMethodType.equals("card") || mList?.get(position)?.vendorPaymentMethodType.equals("netbanking")) {
            holder.paymentModeId.text = Html.fromHtml("<b>Payment Mode : </b>"+"online")
        } else {
            holder.paymentModeId.text = Html.fromHtml("<b>Payment Mode : </b>"+mList?.get(position)?.vendorPaymentMethodType)
        }
        holder.subscribedPackageName.text = Html.fromHtml("<b>Subscribed Package : </b>"+mList?.get(position)?.packageName)
    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var index: TextView = view.findViewById(R.id.textView39)
        var status: TextView = view.findViewById(R.id.textView40)
        var paymentDate: TextView = view.findViewById(R.id.textView41)
        var orderID: TextView = view.findViewById(R.id.textView42)
        var transactionId: TextView = view.findViewById(R.id.transactionId)
        var amount: TextView = view.findViewById(R.id.textView414)
        var paymentModeId: TextView = view.findViewById(R.id.paymentModeId)
        var subscribedPackageName: TextView = view.findViewById(R.id.subscribedPackageName)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}