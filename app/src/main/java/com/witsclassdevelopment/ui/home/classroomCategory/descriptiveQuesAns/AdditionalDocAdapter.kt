package com.witsclassdevelopment.ui.home.classroomCategory.descriptiveQuesAns

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.witsclassdevelopment.R
import com.witsclassdevelopment.service.respose.homeBanner.ImageBanner
import com.github.islamkhsh.CardSliderAdapter

class AdditionalDocAdapter(var studyDocs: ArrayList<String>?, var mPdfInterface: IDescptQuesAnsPdfLoad) :
    CardSliderAdapter<AdditionalDocAdapter.FuturePlusSliderViewHolder>() {
    private var mList: ArrayList<ImageBanner> ?= null
    override fun getItemCount():Int {
        return if (studyDocs == null || studyDocs!!.size == 0) {
            0
        } else {
            studyDocs!!.size;
        }
        //return 10
    }
    public fun updateList(list: ArrayList<ImageBanner>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuturePlusSliderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_single_additional_doc, parent, false)
        return FuturePlusSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FuturePlusSliderViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        /*val item = sliderList?.get(holder.adapterPosition)
        Picasso.get()
            .load(item?.imageUrl)
            .resize(800, 800)
            .centerInside()
            .into(holder.imageSlider)*/

    }

    class FuturePlusSliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //var imageSlider:ImageView = view.findViewById(R.id.sliderImageId)
        var iDoc:CardView = view.findViewById(R.id.iDoc)
        //var iDocName:TextView = view.findViewById(R.id.textView55)
    }

    override fun bindVH(holder: FuturePlusSliderViewHolder, position: Int){
        val item = studyDocs?.get(position)
        //holder.iDocName.text = item
        holder.iDoc.setOnClickListener {
            item?.let { it1 -> mPdfInterface.loadPdf(it1) }
        }

        holder.itemView.run {
            /*Picasso.get()
                .load(item)
                .resize(200, 800)
                .centerInside()
                .into(holder.imageSlider)*/
        }
    }
}