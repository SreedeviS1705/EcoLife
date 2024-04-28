package com.scorepsc.ui.home.newsevents.newsDetails

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import com.scorepsc.R
import com.scorepsc.databinding.FragmentNewsEventDetailsBinding
import com.scorepsc.ui.base.BaseFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsEventsDetailsFragment : BaseFragment(R.layout.fragment_news_event_details) {

    companion object {
        const val TAG = "NewsEventsDetailsFragment"
    }

    private var binding: FragmentNewsEventDetailsBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsEventDetailsBinding.bind(view)

        val title = arguments?.getString("title")
        val image = arguments?.getString("image")
        val newsdetails = arguments?.getString("newsdetails")

        binding?.newsTitle?.text = title
        binding?.newsDetails?.text=if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(newsdetails, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(newsdetails)
        }
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.gallery_placeholder)
            .into(binding?.newsImageId)
        binding

    }
}