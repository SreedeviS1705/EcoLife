package com.scorepsc.ui.home.scholarship.register.exam

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.scorepsc.R
import com.scorepsc.model.QuestionModelData


class ScholarshipSingleQuestionOptionAdapter(var options: ArrayList<QuestionModelData>) :
    RecyclerView.Adapter<ScholarshipSingleQuestionOptionAdapter.ViewHolder>() {


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val mOption: RadioButton = ItemView.findViewById(R.id.radioButtonOption)
        val mOptionClickItem: ConstraintLayout = ItemView.findViewById(R.id.optionClickItem)
        fun bindData(item: QuestionModelData?, options: ArrayList<QuestionModelData>) {
            mOption.text = item?.mQuestionString
            //mOption.isChecked = true
            if (item?.mSelectedStatus == true) {
                Log.d("mOption", "mOption: " + item.mSelectedStatus)
                mOption.isChecked = true
            } else {
                Log.d("mOption", "mOption: " + item?.mSelectedStatus)
                mOption.isChecked = false
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScholarshipSingleQuestionOptionAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_scholarship_single_question_option, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ScholarshipSingleQuestionOptionAdapter.ViewHolder,
        position: Int
    ) {
        val item = options?.get(position)
        Log.d("onBindViewHolder", "" + item)
        holder.bindData(item, options)
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