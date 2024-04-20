package com.witsclassdevelopment.ui.home.classroomCategory.quiz.quizExam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.witsclassdevelopment.R
import com.witsclassdevelopment.model.QuestionModelData
import com.witsclassdevelopment.service.respose.homeBanner.quiz.singlequiz.Question

class ClassQuizQuestionAdapter(var mContext:Context) :
    CardSliderAdapter<ClassQuizQuestionAdapter.FuturePlusSliderViewHolder>() {
    private var mList: ArrayList<Question> ?= null
    private var mAdapter: SingleQuestionOptionAdapter? = null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 10
    }
    public fun updateList(list: ArrayList<Question>?) {
        mList = list
        notifyDataSetChanged()
    }

    fun getList(): List<Question>? {
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
    }

    override fun bindVH(holder: FuturePlusSliderViewHolder, position: Int){
        val item = mList?.get(position)
        holder.indexPosition.text = ""+(position+1)
        holder.marks.text = item?.marks+" Marks"
        holder.question.text = item?.question

        holder.itemView.run {
            configureRecyclerView(holder.mOptionRecyclerId, item?.optionsList, item?.questionId)
        }
    }

    private fun configureRecyclerView(
        recyclerView: RecyclerView,
        options: ArrayList<QuestionModelData>?,
        questionId: String?
    ) {
       /* val answerListModel: ArrayList<QuestionModelData> = ArrayList()
        if (options != null) {
            for (item in options) {
                answerListModel.add(QuestionModelData(false, item, questionId?.toInt()))
            }
        }*/
        mAdapter = options?.let { SingleQuestionOptionAdapter(it) }
        val layoutManager = LinearLayoutManager(mContext)
        recyclerView.layoutManager = layoutManager
        //recyclerView.layoutManager = GridLayoutManager(mContext, 2)
        /*recyclerView.layoutManager = LinearLayoutManager(
            mContext,
            LinearLayoutManager.VERTICAL,
            false
        )*/
        recyclerView?.adapter = mAdapter
    }
}