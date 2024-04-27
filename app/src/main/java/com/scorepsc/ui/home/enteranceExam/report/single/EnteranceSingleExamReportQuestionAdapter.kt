package com.scorepsc.ui.home.enteranceExam.report.single

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.squareup.picasso.Picasso
import com.scorepsc.R
import com.scorepsc.service.respose.homeBanner.enteranceExam.report.singleReportResponse.Report

class EnteranceSingleExamReportQuestionAdapter(var mContext:Context) :
    CardSliderAdapter<EnteranceSingleExamReportQuestionAdapter.FuturePlusSliderViewHolder>() {
    private var mList: ArrayList<Report> ?= null
    private var mAdapter: EnteranceSingleQuestionOptionReportAdapter? = null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 10
    }
    public fun updateList(list: ArrayList<Report>?) {
        mList = list
        notifyDataSetChanged()
    }

    fun getList(): List<Report>? {
        return mList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuturePlusSliderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_quiz_question_single_item, parent, false)
        return FuturePlusSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FuturePlusSliderViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        /*val item = sliderList?.get(holder.adapterPosition)*/

    }

    class FuturePlusSliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //var imageSlider:ImageView = view.findViewById(R.id.sliderImageId)
        var mOptionRecyclerId:RecyclerView = view.findViewById(R.id.optionRecyclerId)
        var indexPosition:TextView = view.findViewById(R.id.indexPosition)
        var marks:TextView = view.findViewById(R.id.marks)
        var question:TextView = view.findViewById(R.id.textView13)
        var correctAnswer:TextView = view.findViewById(R.id.correctAnswer)
        var questionImage:ImageView = view.findViewById(R.id.questionImage)
        var correctAnswerImage:ImageView = view.findViewById(R.id.correctAnswerImage)
    }

    override fun bindVH(holder: FuturePlusSliderViewHolder, position: Int){
        val item = mList?.get(position)
        holder.indexPosition.text = ""+(position+1)
        holder.marks.text = item?.marks+" Marks"
        holder.question.text = item?.question

        if(!item?.optionsList.isNullOrEmpty()) {
            for (listItem in item?.optionsList!!) {
                if(listItem?.mWrongAnswerStatus?.equals("fail") == true) {
                    if(listItem?.mCorrectAnswerStatus?.ptype?.equals("image") == true) {
                        holder.correctAnswerImage.visibility = View.VISIBLE
                        holder.correctAnswer.text = "Correct Answer: "
                        Picasso.get()
                            .load(listItem?.mCorrectAnswerStatus!!.ptext)
                            .resize(200, 800)
                            .centerInside()
                            .into(holder?.correctAnswerImage)
                    } else {
                        holder.correctAnswerImage.visibility = View.GONE
                        holder.correctAnswer.text = "Correct Answer: "+listItem?.mCorrectAnswerStatus?.ptext
                    }
                    break
                } else {
                    holder.correctAnswer.text = ""
                }
            }
        }


        holder.itemView.run {
            configureRecyclerView(holder.mOptionRecyclerId, item?.optionsList, item?.questionId)
        }

        if (!item?.questionImage.isNullOrEmpty()) {
            holder.questionImage.visibility = View.VISIBLE
            Picasso.get()
                .load(item?.questionImage)
                .resize(200, 800)
                .centerInside()
                .into(holder.questionImage)
        } else {
            holder.questionImage.visibility = View.GONE
        }
    }

    private fun configureRecyclerView(
        recyclerView: RecyclerView,
        options: ArrayList<EnteranceReportQuestionModelData>?,
        questionId: String?
    ) {
       /* val answerListModel: ArrayList<QuestionModelData> = ArrayList()
        if (options != null) {
            for (item in options) {
                answerListModel.add(QuestionModelData(false, item, questionId?.toInt()))
            }
        }*/
        mAdapter = options?.let { EnteranceSingleQuestionOptionReportAdapter(it, mContext) }
        val layoutManager = LinearLayoutManager(mContext)
        //recyclerView.layoutManager = layoutManager
        //recyclerView.layoutManager = GridLayoutManager(mContext, 2)
        recyclerView.layoutManager = LinearLayoutManager(
            mContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerView?.adapter = mAdapter
    }
}