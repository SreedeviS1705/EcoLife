package com.scorepsc.ui.home

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.scorepsc.R
import com.scorepsc.service.respose.homeBanner.latestUpdates.LatestUpdateResponse
import com.github.islamkhsh.CardSliderAdapter


class LatestUpdatesAdapter(var activity: FragmentActivity?, var mContext:IScrollClick) :
    CardSliderAdapter<LatestUpdatesAdapter.SubjectViewHolder>() {
    companion object {
        const val TAG = "LatestUpdatesAdapter"
    }

    private var mList: ArrayList<LatestUpdateResponse.Datum>? = ArrayList()
    override fun getItemCount(): Int {
        /*return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size+Int.MAX_VALUE;
        }*/
        return Int.MAX_VALUE
    }

    public fun updateList(list: ArrayList<LatestUpdateResponse.Datum>) {
        mList?.clear()
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_updates_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if(mList?.size != null && position < mList?.size!!) {
            if (mList?.size == position) {
                val mTempList = mList
                try {
                    for (item in mTempList!!) {
                        mList?.add(item)
                    }
                } catch (e: Exception) {
                    Log.d("LatestUpdatesAdapter", "Exception: $e")
                }
            }
            holder.message.text = mList?.get(position)?.title
            holder.scrollContainer.setOnClickListener {
                Log.d(TAG, "clickPosition: $position")
                if(mList?.get(position)?.type.equals("link")) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mList?.get(position)?.refValue))
                    activity?.startActivity(browserIntent)
                } else if(mList?.get(position)?.type.equals("batch")) {
                    mContext.clickEvent(mList?.get(position))
                }
            }
        }
    }

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var message: TextView = view.findViewById(R.id.message)
        var scrollContainer: ConstraintLayout = view.findViewById(R.id.scrollContainer)
    }

    override fun bindVH(holder: SubjectViewHolder, position: Int) {
        //val item = mList?.get(position)
    }
}