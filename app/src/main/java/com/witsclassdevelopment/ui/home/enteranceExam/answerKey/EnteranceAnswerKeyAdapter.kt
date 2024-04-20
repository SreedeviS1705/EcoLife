package com.witsclassdevelopment.ui.home.enteranceExam.answerKey

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.squareup.picasso.Picasso
import com.witsclassdevelopment.ImageZoomActivity
import com.witsclassdevelopment.R
import com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.answerkey.EnteranceExamAnswerKeyResponse
import com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.answerkey.Explanation

class EnteranceAnswerKeyAdapter(var mContext:Context?) :
    CardSliderAdapter<EnteranceAnswerKeyAdapter.FuturePlusSliderViewHolder>() {
    private var mList: ArrayList<EnteranceExamAnswerKeyResponse.Datum> ?= null
    private var mAdapter: ExplanationAdapter? = null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 10
    }
    public fun updateList(list: ArrayList<EnteranceExamAnswerKeyResponse.Datum>?) {
        mList = list
        notifyDataSetChanged()
    }

    fun getList(): List<EnteranceExamAnswerKeyResponse.Datum>? {
        return mList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuturePlusSliderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_answer_key_single_item, parent, false)
        return FuturePlusSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FuturePlusSliderViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

    }

    class FuturePlusSliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.textView112)
        var questionImageId: ImageView = view.findViewById(R.id.questionImageId)
        var answer: TextView = view.findViewById(R.id.answer)
        var explanationRecyclerviewId: RecyclerView = view.findViewById(R.id.explanationRecyclerviewId)
        var correctAnswerImage: ImageView = view.findViewById(R.id.correctAnswerImage)
    }

    override fun bindVH(holder: FuturePlusSliderViewHolder, position: Int){
        val item = mList?.get(position)
        holder.title.text = ""+(position+1)+") "+item?.question
        if(item?.questionImage != null) {
            holder.questionImageId.visibility = View.VISIBLE
            Picasso.get()
                .load(item?.questionImage)
                .resize(200, 800)
                .centerInside()
                .into(holder.questionImageId)
        } else {
            holder.questionImageId.visibility = View.GONE
        }
        if(item?.correctOption?.ptype?.equals("image") == true) {
            holder.answer.visibility = View.GONE
            holder.correctAnswerImage.visibility = View.VISIBLE
            Picasso.get()
                .load(item?.correctOption!!.ptext)
                .resize(200, 800)
                .centerInside()
                .into(holder.correctAnswerImage)
        } else {
            holder.correctAnswerImage.visibility = View.GONE
            holder.answer.visibility = View.VISIBLE
            holder.answer.text = item?.correctOption?.ptext
        }
        holder.questionImageId.setOnClickListener {
            Log.d("TAG", "study_written_notes_img: click")
            val intent = Intent(mContext, ImageZoomActivity::class.java)
            intent.putExtra("image_url", item?.questionImage)
            mContext?.startActivity(intent)
        }
        configureRecyclerView(holder.explanationRecyclerviewId, item?.explanation)
    }

    private fun configureRecyclerView(
        recyclerView: RecyclerView,
        explanation: ArrayList<Explanation>?
    ) {
        mAdapter = ExplanationAdapter(explanation, mContext)
        val layoutManager = LinearLayoutManager(mContext)
        recyclerView.layoutManager = layoutManager
        recyclerView?.adapter = mAdapter
    }
}