package com.welkinwits.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.welkinwits.R
import com.welkinwits.model.QuestionModelData
import com.welkinwits.service.respose.homeBanner.exam.singleExam.Question


class SingleExamListingAdapter(var applicationContext: Context) :
    RecyclerView.Adapter<SingleExamListingAdapter.ViewHolder>() {
    //private var mList: ArrayList<Request>? = null
    private var mList: List<Question>? = null
    private var mAdapter: SingleQuestionOptionListingAdapter? = null

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val mIndexPosition: TextView = ItemView.findViewById(R.id.indexPosition)
        val mQuestion: TextView = ItemView.findViewById(R.id.textView13)
        val mMarks: TextView = ItemView.findViewById(R.id.marks)
        val mOptionRecyclerId: RecyclerView = ItemView.findViewById(R.id.optionRecyclerId)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SingleExamListingAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_single_exam, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SingleExamListingAdapter.ViewHolder, position: Int) {
        val item = mList?.get(position)
        holder.mIndexPosition.text = "" + (position + 1)
        holder.mQuestion.text = item?.question
        holder.mMarks.text = item?.marks + " Marks"
        configureRecyclerView(holder.mOptionRecyclerId, item?.optionsList, item?.questionId)

    }

    override fun getItemCount(): Int {
        return if (mList == null || mList!!.isEmpty()) {
            0
        } else {
            mList!!.size
        }
    }

    fun updateList(list: List<Question>?) {
        mList = list
        notifyDataSetChanged()
    }

    fun getList(): List<Question>? {
        return mList
    }

    private fun configureRecyclerView(
        recyclerView: RecyclerView,
        options: ArrayList<QuestionModelData>?,
        questionId: String?
    ) {
        /*val answerListModel: ArrayList<QuestionModelData> = ArrayList()
        if (options != null) {
            for (item in options) {
                answerListModel.add(QuestionModelData(false, item, questionId?.toInt()))
            }
        }*/
        mAdapter = options?.let { SingleQuestionOptionListingAdapter(it) }
        val layoutManager = GridLayoutManager(applicationContext,2)
        recyclerView.layoutManager = layoutManager
        //recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
       /* recyclerView.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )*/
        recyclerView?.adapter = mAdapter
    }
}