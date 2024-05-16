package com.welkinwits.ui.home.importantLink

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentImportantLinksBinding
import com.welkinwits.service.respose.homeBanner.importantLink.ImportantLinkResponse
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImportantLinksFragment : BaseFragment(R.layout.fragment_important_links), IImportantLink {

    companion object {
        const val TAG = "ILFragment"
    }

    private val viewModel: ImportantLinkViewModel by viewModels()
    private var binding: FragmentImportantLinksBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImportantLinksBinding.bind(view)

        viewModel.getImportantLink()

        val mAdapter = ImportantLinkAdapter(this)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.importantLinkResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "importantLinkResponse: "+it.data?.data)
            it.data?.data?.let { it1 -> mAdapter.updateList(it1) }
        }

        viewModel.importantLinkErrorMessage.observe(viewLifecycleOwner) {
        }

    }

    override fun clickEvent(item: ImportantLinkResponse.Datum) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
        startActivity(browserIntent)
    }
}