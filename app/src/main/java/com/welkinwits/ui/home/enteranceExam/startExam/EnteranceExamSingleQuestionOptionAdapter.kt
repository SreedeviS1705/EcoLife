package com.welkinwits.ui.home.enteranceExam.startExam

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.welkinwits.R
import com.welkinwits.model.EnteranceExamQuestionModelData


class EnteranceExamSingleQuestionOptionAdapter(var options: ArrayList<EnteranceExamQuestionModelData>) :
    RecyclerView.Adapter<EnteranceExamSingleQuestionOptionAdapter.ViewHolder>() {


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val mOption: RadioButton = ItemView.findViewById(R.id.radioButtonOption)
        val mOptionClickItem: ConstraintLayout = ItemView.findViewById(R.id.optionClickItem)
        val optionImage: ImageView = ItemView.findViewById(R.id.optionImage)
        val labelId: TextView = ItemView.findViewById(R.id.labelId)
        fun bindData(item: EnteranceExamQuestionModelData?, options: ArrayList<EnteranceExamQuestionModelData>) {
            if(item?.mQuestion?.ptype?.equals("image") == true) {
                labelId.visibility = View.GONE
                optionImage.visibility = View.VISIBLE
                Picasso.get()
                    .load(item?.mQuestion!!.ptext)
                    .resize(200, 800)
                    .centerInside()
                    .into(optionImage)
            } else {
                optionImage.visibility = View.GONE
                labelId.text = item?.mQuestion?.ptext
            }
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
    ): EnteranceExamSingleQuestionOptionAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_enterance_exam_single_question_option, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: EnteranceExamSingleQuestionOptionAdapter.ViewHolder,
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
            item?.mQuestionIndex = (position+1)
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