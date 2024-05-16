package com.welkinwits.ui.home.classroomCategory

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.welkinwits.R
import com.github.islamkhsh.CardSliderAdapter
import com.squareup.picasso.Picasso
import com.welkinwits.ImageZoomActivity
import com.welkinwits.service.respose.homeBanner.chapterWiseStudyNotes.Content

class ClassNotesContentAdapter(var mList: ArrayList<Content>?, var mContext: Context) :
    CardSliderAdapter<ClassNotesContentAdapter.FuturePlusSliderViewHolder>() {
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 10
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuturePlusSliderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_single_chapter_notes_content, parent, false)
        return FuturePlusSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FuturePlusSliderViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

    }

    class FuturePlusSliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var contentText: TextView = view.findViewById(R.id.contentText)
        var contentImage: ImageView = view.findViewById(R.id.contentImage)
    }

    override fun bindVH(holder: FuturePlusSliderViewHolder, position: Int){
        val item = mList?.get(position)

        holder.contentImage.setOnClickListener {
            if (item?.ptype.equals("image")) {
                Log.d("TAG", "contentImage: click>>"+item?.pdata)
                val intent = Intent(mContext, ImageZoomActivity::class.java)
                intent.putExtra("image_url", item?.pdata)
                mContext.startActivity(intent)
            }
        }


        holder.itemView.run {
            if(item?.ptype.equals("image")) {
                holder.contentText.visibility = View.GONE
                holder.contentImage.visibility = View.VISIBLE
                Picasso.get()
                .load(item?.pdata)
                .into(holder.contentImage)
            } else {
                holder.contentText.visibility = View.VISIBLE
                holder.contentImage.visibility = View.GONE
                holder.contentText.text = item?.pdata
            }

        }
    }
}