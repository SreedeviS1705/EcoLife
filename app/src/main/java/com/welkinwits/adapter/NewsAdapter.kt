package com.welkinwits.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.welkinwits.databinding.LayoutNewsItemBinding
import com.welkinwits.service.respose.NewsResponse

class NewsAdapter : ListAdapter<NewsResponse.NewsData, NewsAdapter.NewsViewHolder>(Companion) {

    private lateinit var onItemClick: (item: NewsResponse.NewsData) -> Unit

    class NewsViewHolder(val binding: LayoutNewsItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<NewsResponse.NewsData>() {
        override fun areItemsTheSame(
            oldItem: NewsResponse.NewsData,
            newItem: NewsResponse.NewsData
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: NewsResponse.NewsData,
            newItem: NewsResponse.NewsData
        ): Boolean = oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutNewsItemBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.binding.title.text = newsItem.heading
        holder.binding.desc.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(newsItem.news, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(newsItem.news)
        }
        holder.binding.thumbnail.load(newsItem.images) {
            crossfade(true)
            placeholder(ColorDrawable(Color.LTGRAY))
            transformations(RoundedCornersTransformation(8f))
        }
        holder.itemView.setOnClickListener {
            onItemClick.invoke(newsItem)
        }


    }

    fun onItemClick(onItemClick: (NewsResponse.NewsData) -> Unit) {
        this.onItemClick = onItemClick
    }
}