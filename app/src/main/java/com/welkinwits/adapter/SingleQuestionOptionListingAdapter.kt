package com.welkinwits.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.welkinwits.R
import com.welkinwits.model.QuestionModelData


class SingleQuestionOptionListingAdapter(var options: ArrayList<QuestionModelData>) :
    RecyclerView.Adapter<SingleQuestionOptionListingAdapter.ViewHolder>() {


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val mOption: RadioButton = ItemView.findViewById(R.id.radioButtonOption)
        val mOptionClickItem: ConstraintLayout = ItemView.findViewById(R.id.optionClickItem)
        val optionText: TextView = ItemView.findViewById(R.id.optionText)
        fun bindData(item: QuestionModelData, options: ArrayList<QuestionModelData>) {
            optionText.text = item.mQuestionString
            if (item.mSelectedStatus) {
                Log.d("mOption", "mOption: " + item.mSelectedStatus)
                mOption.isChecked = true
            } else {
                Log.d("mOption", "mOption: " + item.mSelectedStatus)
                mOption.isChecked = false
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SingleQuestionOptionListingAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_single_question_option, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: SingleQuestionOptionListingAdapter.ViewHolder,
        position: Int
    ) {
        val item = options?.get(position)
        Log.d("onBindViewHolder", "" + item)
        item?.let { holder.bindData(it, options) }
        holder.mOptionClickItem.setOnClickListener {
            Log.d("mOption", "mOption clicked")
            for (singleItem in options) {
                singleItem.mSelectedStatus = false
            }
            item?.mSelectedStatus = true
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return if (options == null || options!!.isEmpty()) {
            0
        } else {
            options!!.size
        }
    }
}