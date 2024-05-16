package com.welkinwits.ui.home.currentAffairs.byGroup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.welkinwits.R
import com.github.islamkhsh.CardSliderAdapter
import com.squareup.picasso.Picasso
import com.welkinwits.service.respose.homeBanner.currentAfairs.byGroup.Post

class CurrentAffairsByGroupAdapter(var mContext:ICurrentAffairsByGroup) :
    CardSliderAdapter<CurrentAffairsByGroupAdapter.SubjectViewHolder>() {
    private var mList: ArrayList<Post> ?= null
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
    }
    public fun updateList(list: ArrayList<Post>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_current_affairs_by_group_sigle_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val item = mList?.get(position)
        holder.heading.text = item?.title
        holder.container.setOnClickListener {
            mContext.clickEvent(item)
        }

        Picasso.get()
                .load(item?.image)
                .resize(200, 800)
                .centerInside()
                .into(holder.CAImage)

    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var heading: TextView = view.findViewById(R.id.heading)
        var CAImage: ImageView = view.findViewById(R.id.imageView45)
        var container: CardView = view.findViewById(R.id.container)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int){
        val item = mList?.get(position)
    }
}