package com.scorepsc.ui.home.enteranceExam.startExam

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.squareup.picasso.Picasso
import com.scorepsc.ImageZoomActivity
import com.scorepsc.R
import com.scorepsc.model.EnteranceExamQuestionModelData
import com.scorepsc.service.respose.homeBanner.enteranceExam.startExam.Question

class EnteranceStartExamAdapter(var mContext:Context) :
    CardSliderAdapter<EnteranceStartExamAdapter.FuturePlusSliderViewHolder>() {
    private var mList: ArrayList<Question> ?= null
    private var mAdapter: EnteranceExamSingleQuestionOptionAdapter? = null
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
            LayoutInflater.from(parent.context).inflate(R.layout.layout_enterance_start_exam_single_item, parent, false)
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
        var negativeMark:TextView = view.findViewById(R.id.textView108)
        var question:TextView = view.findViewById(R.id.textView13)
        var questionImageView:ImageView = view.findViewById(R.id.imageView47)
    }

    override fun bindVH(holder: FuturePlusSliderViewHolder, position: Int){
        val item = mList?.get(position)
        holder.indexPosition.text = ""+(position+1)
        holder.marks.text = item?.marks+""
        holder.negativeMark.text = "  -"+item?.negativeMarks
        holder.question.text = item?.question
        Log.d("ROSHANEST", "(position+1)" + (position+1) + "item?.question "+item?.question)

        holder.itemView.run {
            configureRecyclerView(holder.mOptionRecyclerId, item?.optionsList, item?.id)
        }
        if(item?.questionImage!= null) {
            holder.questionImageView.visibility = View.VISIBLE
            Picasso.get()
                .load(item?.questionImage)
                .into(holder.questionImageView)
        } else {
            holder.questionImageView.visibility = View.GONE
        }
        holder.questionImageView.setOnClickListener {
            Log.d("TAG", "study_written_notes_img: click")
            val intent = Intent(mContext, ImageZoomActivity::class.java)
            intent.putExtra("image_url", item?.questionImage)
            mContext?.startActivity(intent)
        }

    }

    private fun configureRecyclerView(
        recyclerView: RecyclerView,
        options: ArrayList<EnteranceExamQuestionModelData>?,
        questionId: String?
    ) {
       /* val answerListModel: ArrayList<QuestionModelData> = ArrayList()
        if (options != null) {
            for (item in options) {
                answerListModel.add(QuestionModelData(false, item, questionId?.toInt()))
            }
        }*/
        mAdapter = options?.let { EnteranceExamSingleQuestionOptionAdapter(it) }
        val layoutManager = LinearLayoutManager(mContext)
        //recyclerView.layoutManager = layoutManager
        recyclerView.layoutManager = GridLayoutManager(mContext, 2)
        recyclerView.layoutManager = LinearLayoutManager(
            mContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerView?.adapter = mAdapter
    }
}