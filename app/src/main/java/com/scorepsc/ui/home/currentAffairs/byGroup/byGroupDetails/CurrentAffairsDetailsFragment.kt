package com.scorepsc.ui.home.currentAffairs.byGroup.byGroupDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import com.scorepsc.R
import com.scorepsc.databinding.FragmentCurrentAffairsDetailsBinding
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CurrentAffairsDetailsFragment : BaseFragment(R.layout.fragment_current_affairs_details) {

    companion object {
        const val TAG = "CurrentAffairsDetailsFragment"
    }

    private val viewModel: CurrentAffairsDetailsViewModel by viewModels()
    private var binding: FragmentCurrentAffairsDetailsBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCurrentAffairsDetailsBinding.bind(view)

        val postId = arguments?.getString("post_id")
        postId?.let {
            viewModel.getCurrentAffairsDetails(postId.toInt())
        }



        viewModel.currentAfairsdetailsResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "currentAfairsdetailsResponse: ")
            binding?.textView113?.text = it?.data?.data?.title
            binding?.descriptionId?.text = it?.data?.data?.description
            Picasso.get()
                .load(it?.data?.data?.image)
                .into(binding?.imageView46)
        }

        viewModel.currentAfairsdetailsErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "currentAfairsdetailsErrorMessage: ")
            Toast.makeText(context, "Error loading", Toast.LENGTH_LONG).show()
        }

    }
/*
    override fun clickEvent(item: DemoVideosResponse.Datum?) {
        val intent = Intent(requireActivity(), PlayerActivity::class.java)
        intent.putExtra("URL", item?.videoLink)
        startActivity(intent)

    }*/
}