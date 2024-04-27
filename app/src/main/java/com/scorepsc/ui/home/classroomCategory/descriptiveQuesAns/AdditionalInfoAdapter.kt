package com.scorepsc.ui.home.classroomCategory.descriptiveQuesAns

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.scorepsc.R
import com.scorepsc.service.respose.homeBanner.ImageBanner
import com.github.islamkhsh.CardSliderAdapter
import com.squareup.picasso.Picasso
import com.scorepsc.ImageZoomActivity

class AdditionalInfoAdapter(var writtenNotes: ArrayList<String>?, var mContext: Context) :
    CardSliderAdapter<AdditionalInfoAdapter.FuturePlusSliderViewHolder>() {
    private var mList: ArrayList<ImageBanner> ?= null
    override fun getItemCount():Int {
        return if (writtenNotes == null || writtenNotes!!.size == 0) {
            0
        } else {
            writtenNotes!!.size;
        }
        //return 10
    }
    public fun updateList(list: ArrayList<ImageBanner>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuturePlusSliderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_single_chapter_written_notes, parent, false)
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
        var study_written_notes_img: ImageView = view.findViewById(R.id.study_written_notes_img)
    }

    override fun bindVH(holder: FuturePlusSliderViewHolder, position: Int){
        val item = writtenNotes?.get(position)
        holder.study_written_notes_img.setOnClickListener {
            Log.d("TAG", "study_written_notes_img: click")
            val intent = Intent(mContext, ImageZoomActivity::class.java)
            intent.putExtra("image_url", item)
            mContext.startActivity(intent)
        }

        holder.itemView.run {
            Picasso.get()
                .load(item)
                .resize(200, 200)
                .centerInside()
                .into(holder.study_written_notes_img)
        }
    }
}