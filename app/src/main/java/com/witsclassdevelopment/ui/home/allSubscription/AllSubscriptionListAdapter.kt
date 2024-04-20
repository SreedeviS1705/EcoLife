package com.witsclassdevelopment.ui.home.allSubscription

import android.content.Context
import android.graphics.Paint
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.witsclassdevelopment.R
import com.witsclassdevelopment.service.respose.homeBanner.subscription.GetAllSubscriptionListResponse


class AllSubscriptionListAdapter(var mContext:Context, var mClickContext:IAllSubscriptionList) :
    CardSliderAdapter<AllSubscriptionListAdapter.FuturePlusSliderViewHolder>() {
    private var mList: ArrayList<GetAllSubscriptionListResponse.HomeBannerData> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 10
    }
    public fun updateList(list: ArrayList<GetAllSubscriptionListResponse.HomeBannerData>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuturePlusSliderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_single_all_subsciption_list, parent, false)
        return FuturePlusSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FuturePlusSliderViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val item = mList?.get(holder.adapterPosition)
    }

    class FuturePlusSliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title:TextView = view.findViewById(R.id.textView86)
        var description:TextView = view.findViewById(R.id.textView87)
        var daysLeft:TextView = view.findViewById(R.id.textView88)
        var offerAmount:TextView = view.findViewById(R.id.textView89)
        var originalAmount:TextView = view.findViewById(R.id.originalAmount)
        var subscribeBtn:ConstraintLayout = view.findViewById(R.id.subscribeBtn)
        var btnText:TextView = view.findViewById(R.id.textView90)
        var subscriptionEndDate:TextView = view.findViewById(R.id.textView101)
        var subscriptionButtonIc:ImageView = view.findViewById(R.id.imageView36)
        var subscriptionListItem:CardView = view.findViewById(R.id.subscriptionListItem)
    }

    override fun bindVH(holder: FuturePlusSliderViewHolder, position: Int){
        val item = mList?.get(position)
        holder.itemView.run {
            holder.title?.text = item?.packageName
            holder.description?.text = item?.description
            holder.daysLeft?.text = item?.tenureCount+ " Days "
            holder.offerAmount?.text = " Price: Rs "+item?.offerAmount
            holder.originalAmount?.text = "Original price : INR "+item?.amount
            holder.originalAmount?.setPaintFlags(holder.originalAmount?.getPaintFlags()!! or Paint.STRIKE_THRU_TEXT_FLAG)
            if(item?.subscriptionStatus.equals("1")) {
                holder.subscribeBtn.background = ContextCompat.getDrawable(context, R.drawable.round_background_subscription_btn)
                holder.subscriptionButtonIc.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.tick_ic))
                holder.btnText.setTextColor(resources.getColor(R.color.text_green))
                holder.subscriptionEndDate.visibility = View.VISIBLE
                val next = "Subscription will end on <font color='#18C908'>"+item?.subscriptionEndDate+"</font>"
                holder.subscriptionEndDate.text = Html.fromHtml(next)
                //holder.subscriptionEndDate.text = "Subscription will end on <font color='#EE0000'>"+item?.subscriptionEndDate+"</font>"
                holder.btnText.text = "Subscribed"
            } else {
                holder.subscribeBtn.background = ContextCompat.getDrawable(context, R.drawable.round_background_subscription_btn_not_subscribed)
                holder.subscriptionButtonIc.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.subscription_star_ic))
                holder.btnText.setTextColor(resources.getColor(R.color.fail))
                holder.subscriptionEndDate.visibility = View.GONE
                holder.btnText.text = "Subscribe Now"
            }
            holder?.subscribeBtn?.setOnClickListener {
                if(!item?.subscriptionStatus.equals("1")) {
                    mClickContext.clickEvent(item)
                } else {
                    Log.d("subscriptionListItem", ""+item?.subscriptionStatus)
                }
            }

        }
    }
}