package com.scorepsc.ui.home.probableQA

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.scorepsc.R
import com.scorepsc.service.respose.homeBanner.probableQA.ProbableQAGroupResponse

class ProbabileQAGroupAdapter(var mClick:IProbableQAGroup) :
    CardSliderAdapter<ProbabileQAGroupAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<ProbableQAGroupResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 5
    }
    public fun updateList(list: ArrayList<ProbableQAGroupResponse.Datum>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_probable_qa_group_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.title.text = mList?.get(position)?.name
        /*Picasso.get().load(mList?.get(position)?.images).into(holder.imageView)*/
        holder.subjectContainer.setOnClickListener {
            mList?.get(position)?.let { it1 -> mClick.clickEvent(it1) }
        }

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
        var subjectContainer: CardView = view.findViewById(R.id.subjectContainer)
/*        var container: CardView = view.findViewById(R.id.subjectContainer)
        var imageView: ImageView = view.findViewById(R.id.imageView19)*/
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}