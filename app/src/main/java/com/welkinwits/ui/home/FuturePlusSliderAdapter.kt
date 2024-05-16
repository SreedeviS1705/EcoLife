package com.welkinwits.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.welkinwits.R
import com.welkinwits.service.respose.homeBanner.ImageBanner
import com.github.islamkhsh.CardSliderAdapter
import com.squareup.picasso.Picasso

class FuturePlusSliderAdapter(var mContext:IHomeListing) :
    CardSliderAdapter<FuturePlusSliderAdapter.FuturePlusSliderViewHolder>() {
    private var mList: ArrayList<ImageBanner> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<ImageBanner>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuturePlusSliderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.slider_single_item, parent, false)
        return FuturePlusSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FuturePlusSliderViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        /*val item = sliderList?.get(holder.adapterPosition)
        Picasso.get()
            .load(item?.imageUrl)
            .resize(800, 800)
            .centerInside()
            .into(holder.imageSlider)*/

    }

    class FuturePlusSliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageSlider:ImageView = view.findViewById(R.id.sliderImageId)
    }

    override fun bindVH(holder: FuturePlusSliderViewHolder, position: Int){
        val item = mList?.get(position)

        holder.itemView.run {
            Picasso.get()
                .load(item?.banner)
                .resize(1000, 800)
                .centerInside()
                .into(holder.imageSlider)
            holder.imageSlider.setOnClickListener {
                item?.link?.let { it1 -> mContext.clickSliderItem(it1) }
            }

        }
    }
}