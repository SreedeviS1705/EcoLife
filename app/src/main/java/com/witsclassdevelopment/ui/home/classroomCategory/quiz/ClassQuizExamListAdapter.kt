package com.witsclassdevelopment.ui.home.classroomCategory.quiz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.witsclassdevelopment.R
import com.witsclassdevelopment.adapter.SingleQuestionOptionListingAdapter
import com.witsclassdevelopment.service.respose.homeBanner.quiz.GetQuizQuestionResponse

class ClassQuizExamListAdapter(var mContext: Context, var mClickEvent:IClassQuizExamList) :
    CardSliderAdapter<ClassQuizExamListAdapter.FuturePlusSliderViewHolder>() {
    private var mList: ArrayList<GetQuizQuestionResponse.Datum> ?= null
    private var mAdapter: SingleQuestionOptionListingAdapter? = null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 10
    }
    public fun updateList(list: ArrayList<GetQuizQuestionResponse.Datum>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuturePlusSliderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_quiz_exam_list_single_item, parent, false)
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
        var createdOn:TextView = view.findViewById(R.id.textView61)
        var name:TextView = view.findViewById(R.id.textView60)
        var resultText:TextView = view.findViewById(R.id.textView62)
        var actionTextId:TextView = view.findViewById(R.id.actionTextId)
        var attendedActionText:TextView = view.findViewById(R.id.textView63)
        var rootView:CardView = view.findViewById(R.id.rootView)
    }

    override fun bindVH(holder: FuturePlusSliderViewHolder, position: Int){
        val item = mList?.get(position)

        if(item?.status.equals("1")) {
            holder.resultText.visibility = View.GONE
            holder.actionTextId.text = "Practice Quiz"
            holder.actionTextId.setTextColor(ContextCompat.getColor(mContext, R.color.blue_200))
            holder.attendedActionText.visibility = View.GONE
        } else {
            holder.resultText.visibility = View.VISIBLE
            holder.actionTextId.text = "Practice again"
            holder.actionTextId.setTextColor(ContextCompat.getColor(mContext, R.color.grey))
            holder.attendedActionText.visibility = View.VISIBLE
        }

        holder.itemView.run {
            /*Picasso.get()
                .load(item)
                .resize(200, 800)
                .centerInside()
                .into(holder.imageSlider)*/
            holder.createdOn.text = "Quiz Created on "+item?.addedOn
            holder.name.text = item?.name
            holder.rootView.setOnClickListener {
                mClickEvent.clickEvent(item)
            }
        }
    }
}