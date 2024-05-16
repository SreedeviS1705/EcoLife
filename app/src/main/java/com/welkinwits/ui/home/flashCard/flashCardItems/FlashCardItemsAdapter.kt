package com.welkinwits.ui.home.flashCard.flashCardItems

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.squareup.picasso.Picasso
import com.welkinwits.R
import com.welkinwits.service.respose.homeBanner.flashCard.flashcards.FlashCardItemsResponse

class FlashCardItemsAdapter(var mClick:IFlashCardItems) :
    CardSliderAdapter<FlashCardItemsAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<FlashCardItemsResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 5
    }
    public fun updateList(list: ArrayList<FlashCardItemsResponse.Datum>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_flash_card_items_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        var item = mList?.get(position)

        Picasso.get()
            .load(item?.cardImage)

            .into(holder.imageView49)

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
        var imageView49: ImageView = view.findViewById(R.id.imageView49)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}