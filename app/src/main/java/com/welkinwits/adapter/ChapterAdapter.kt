package com.welkinwits.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.welkinwits.databinding.LayoutItemChapterBinding
import com.welkinwits.service.respose.ChapterResponse

class ChapterAdapter :
    ListAdapter<ChapterResponse.ChapterData, ChapterAdapter.ChapterViewHolder>(Companion) {

    private lateinit var onItemClick: (item: ChapterResponse.ChapterData) -> Unit

    class ChapterViewHolder(val binding: LayoutItemChapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<ChapterResponse.ChapterData>() {
        override fun areItemsTheSame(
            oldItem: ChapterResponse.ChapterData,
            newItem: ChapterResponse.ChapterData
        ): Boolean = oldItem === newItem

        override fun areContentsTheSame(
            oldItem: ChapterResponse.ChapterData,
            newItem: ChapterResponse.ChapterData
        ): Boolean = oldItem.chapterId == newItem.chapterId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemChapterBinding.inflate(layoutInflater)
        return ChapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.chapter.text = "Chapter ${position+1} : "+ item.chapterName
        holder.binding.rootView.setOnClickListener {
            onItemClick.invoke(item)
        }
    }

    fun onItemClick(onItemClick: (ChapterResponse.ChapterData) -> Unit) {
        this.onItemClick = onItemClick
    }
}