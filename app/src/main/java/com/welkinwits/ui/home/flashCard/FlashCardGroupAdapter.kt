package com.welkinwits.ui.home.flashCard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.welkinwits.R
import com.welkinwits.service.respose.homeBanner.flashCard.FlashCardGroupResponse

class FlashCardGroupAdapter(var mClick:IFlashCardGroup) :
    CardSliderAdapter<FlashCardGroupAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<FlashCardGroupResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 5
    }
    public fun updateList(list: ArrayList<FlashCardGroupResponse.Datum>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_flash_card_group_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.title.text = mList?.get(position)?.name
        holder.subjectContainer.setOnClickListener {
            mList?.get(position)?.let { it1 -> mClick.clickEvent(it1) }
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
        var subjectContainer: CardView = view.findViewById(R.id.subjectContainer)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}