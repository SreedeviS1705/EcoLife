package com.welkinwits.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.welkinwits.R
import com.welkinwits.databinding.LayoutItemTopicBinding
import com.welkinwits.service.respose.TopicResponse

class TopicAdapter :
    ListAdapter<TopicResponse.TopicData, TopicAdapter.TopicViewHolder>(Companion) {

    private lateinit var onItemClick: (item: TopicResponse.TopicData) -> Unit

    class TopicViewHolder(val binding: LayoutItemTopicBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<TopicResponse.TopicData>() {
        override fun areItemsTheSame(
            oldItem: TopicResponse.TopicData,
            newItem: TopicResponse.TopicData
        ): Boolean = oldItem.topicId == newItem.topicId

        override fun areContentsTheSame(
            oldItem: TopicResponse.TopicData,
            newItem: TopicResponse.TopicData
        ): Boolean = oldItem.topicId == newItem.topicId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemTopicBinding.inflate(layoutInflater, parent, false)
        return TopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.title.text = item.topicName
       // holder.binding.desc.text = item.topicName
        holder.binding.thumbnail.load(item.thumbnail) {
            crossfade(true)
            placeholder(R.drawable.video_cls_ic)
            error(ColorDrawable(Color.LTGRAY))
            transformations(RoundedCornersTransformation(8f))
        }
        holder.itemView.setOnClickListener {
            onItemClick.invoke(item)
        }
    }

    fun onItemClick(onItemClick: (TopicResponse.TopicData) -> Unit) {
        this.onItemClick = onItemClick
    }
}