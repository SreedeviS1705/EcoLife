package com.witsclassdevelopment.ui.home.importantLink

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.witsclassdevelopment.R
import com.github.islamkhsh.CardSliderAdapter
import com.witsclassdevelopment.service.respose.homeBanner.importantLink.ImportantLinkResponse

class ImportantLinkAdapter(var mContext:IImportantLink) :
    CardSliderAdapter<ImportantLinkAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<ImportantLinkResponse.Datum> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 5
    }
    public fun updateList(list: ArrayList<ImportantLinkResponse.Datum>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_important_links_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.titleId.text = mList?.get(position)?.title
        holder.descriptionId.text = mList?.get(position)?.description
        holder.linksId.text = mList?.get(position)?.link
        //Picasso.get().load(mList?.get(position)?.images).into(holder.imageView)
        holder.subjectContainer.setOnClickListener {
            mList?.get(position)?.let { it1 -> mContext.clickEvent(it1) }
        }
    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var linksId: TextView = view.findViewById(R.id.linksId)
        var titleId: TextView = view.findViewById(R.id.titleId)
        var descriptionId: TextView = view.findViewById(R.id.descriptionId)
        var subjectContainer: CardView = view.findViewById(R.id.subjectContainer)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}