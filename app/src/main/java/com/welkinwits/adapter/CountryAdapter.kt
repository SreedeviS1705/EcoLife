package com.welkinwits.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.welkinwits.databinding.LayoutItemCountryBinding
import com.welkinwits.service.respose.CountryResponse
import java.util.*
import kotlin.collections.ArrayList

@Suppress("UNCHECKED_CAST")
open class CountryAdapter(val imageLoader: ImageLoader) :
    ListAdapter<CountryResponse.Data, CountryAdapter.CountryViewHolder>(Companion), Filterable {

    private var originalList: MutableList<CountryResponse.Data>? = null

    private lateinit var onItemClick: (item: CountryResponse.Data) -> Unit

    class CountryViewHolder(val binding: LayoutItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<CountryResponse.Data>() {
        override fun areItemsTheSame(
            oldItem: CountryResponse.Data,
            newItem: CountryResponse.Data
        ): Boolean = oldItem === newItem

        override fun areContentsTheSame(
            oldItem: CountryResponse.Data,
            newItem: CountryResponse.Data
        ): Boolean = oldItem.id == newItem.id
    }

    override fun submitList(list: MutableList<CountryResponse.Data>?) {
        originalList = list
        super.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemCountryBinding.inflate(layoutInflater, null, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.title.text = item.name
        holder.binding.icon.load(item.flag, imageLoader)
        holder.itemView.setOnClickListener {
            onItemClick.invoke(item)
        }
    }

    fun onItemClick(onItemClick: (CountryResponse.Data) -> Unit) {
        this.onItemClick = onItemClick
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                var filteredResults: List<CountryResponse.Data?>? = null
                filteredResults = if (constraint.isEmpty()) {
                    originalList
                } else {
                    getFilteredResults(constraint.toString().toLowerCase(Locale.getDefault()))
                }

                val results = FilterResults()
                results.values = filteredResults
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                val list: MutableList<CountryResponse.Data> =
                    results?.values as MutableList<CountryResponse.Data>
                submitList(list, {})
            }

        }
    }

    protected fun getFilteredResults(constraint: String): List<CountryResponse.Data> {
        val results: MutableList<CountryResponse.Data> = ArrayList()
        for (item in originalList!!) {
            if (item.name.toLowerCase(Locale.getDefault()).contains(constraint)) {
                results.add(item)
            }
        }
        return results
    }
}