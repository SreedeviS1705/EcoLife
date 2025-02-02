package com.welkinwits.ui.home.probableQA.answerKey

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.squareup.picasso.Picasso
import com.welkinwits.ImageZoomActivity
import com.welkinwits.R
import com.welkinwits.service.respose.homeBanner.probableQA.qaAnswerKey.Explanation

class QAExplanationAdapter(var explanation: ArrayList<Explanation>?, var mContext:Context?) :
    CardSliderAdapter<QAExplanationAdapter.FuturePlusSliderViewHolder>() {
    override fun getItemCount():Int {
        return if (explanation == null || explanation!!.size == 0) {
            0
        } else {
            explanation!!.size;
        }
        //return 2
    }

    fun getList(): ArrayList<Explanation>? {
        return explanation
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuturePlusSliderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_explanation_single_item, parent, false)
        return FuturePlusSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FuturePlusSliderViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.explanationImageId.setOnClickListener {
            Log.d("TAG", "study_written_notes_img: click")
            val intent = Intent(mContext, ImageZoomActivity::class.java)
            intent.putExtra("image_url", explanation?.get(position)?.pdata)
            mContext?.startActivity(intent)
        }

    }

    class FuturePlusSliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var explanationImageId: ImageView = view.findViewById(R.id.imageView48)
        var explanationTextId:TextView = view.findViewById(R.id.explanationTextId)
    }

    override fun bindVH(holder: FuturePlusSliderViewHolder, position: Int){
        val item = explanation?.get(position)
        if (item?.ptype.equals("image")) {
            holder.explanationTextId.visibility = View.GONE
            holder.explanationImageId.visibility = View.VISIBLE
            Picasso.get()
                .load(item?.pdata)
                .into(holder.explanationImageId)
        } else {
            holder.explanationTextId.visibility = View.VISIBLE
            holder.explanationImageId.visibility = View.GONE
            holder.explanationTextId.text = item?.pdata
        }

    }

}