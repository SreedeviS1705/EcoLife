package com.welkinwits.ui.home.enteranceExam.report.single

import android.content.Context
import android.content.res.ColorStateList
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


class EnteranceSingleQuestionOptionReportAdapter(
    var options: ArrayList<EnteranceReportQuestionModelData>,
    var mContext: Context) :
    RecyclerView.Adapter<EnteranceSingleQuestionOptionReportAdapter.ViewHolder>() {


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val mOption: RadioButton = ItemView.findViewById(R.id.radioButtonOption)
        val mOptionClickItem: ConstraintLayout = ItemView.findViewById(R.id.optionClickItem)
        val optionImage: ImageView = ItemView.findViewById(R.id.optionImage)
        val optionText: TextView = ItemView.findViewById(R.id.optionText)
        fun bindData(item: EnteranceReportQuestionModelData?, options: ArrayList<EnteranceReportQuestionModelData>) {
            if(item?.mQuestionString?.ptype?.equals("image") == true) {
                optionImage.visibility = View.VISIBLE
                optionText.text = ""
                Picasso.get()
                    .load(item?.mQuestionString!!.ptext)
                    .resize(200, 800)
                    .centerInside()
                    .into(optionImage)
            } else {
                optionImage.visibility = View.GONE
                optionText.text = item?.mQuestionString?.ptext
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
    ): EnteranceSingleQuestionOptionReportAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_single_question_option, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: EnteranceSingleQuestionOptionReportAdapter.ViewHolder,
        position: Int
    ) {
        val item = options?.get(position)
        if(item?.mWrongAnswerStatus.equals("fail")) {
            holder.mOption.buttonTintList = ColorStateList.valueOf(mContext.resources.getColor(R.color.fail))
        } else {
            holder.mOption.buttonTintList = ColorStateList.valueOf(mContext.resources.getColor(R.color.light_green))
        }
        Log.d("onBindViewHolder", "" + item)
        holder.bindData(item, options)
        if(item?.mAllSelectionEnabled!!) {
            holder.mOptionClickItem.setOnClickListener {
                Log.d("mOption", "mOption clicked")
                for (singleItem in options) {
                    singleItem.mSelectedStatus = false
                }
                item?.mSelectedStatus = true
                notifyDataSetChanged()
            }
        } else {
            holder.mOption.isActivated = item?.mAllSelectionEnabled!!
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