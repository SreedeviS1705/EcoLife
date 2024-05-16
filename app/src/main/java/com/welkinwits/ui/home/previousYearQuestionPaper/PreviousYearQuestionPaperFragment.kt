package com.welkinwits.ui.home.previousYearQuestionPaper

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.rajat.pdfviewer.PdfViewerActivity
import com.welkinwits.R
import com.welkinwits.databinding.FragmentPreviousYearQuestionPapersBinding
import com.welkinwits.service.respose.homeBanner.previousYearQuestionPapers.PreviousYearQuestionResponse
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviousYearQuestionPaperFragment : BaseFragment(R.layout.fragment_previous_year_question_papers), IPreviousYearQuestionPapers {

    companion object {
        const val TAG = "PYQPaperFragment"
    }

    private val viewModel: PreviousYearQuestionPaperViewModel by viewModels()
    private var binding: FragmentPreviousYearQuestionPapersBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPreviousYearQuestionPapersBinding.bind(view)
        binding?.progressBarId?.visibility = View.VISIBLE
        viewModel.getPreviousYearPaper()

        val mAdapter = PreviousYearQuestionPapersAdapter(this)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.previousYearQuestionResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "previousYearQuestionResponse: "+it.data?.data)
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                binding?.progressBarId?.visibility = View.GONE
                if (it?.data?.data?.isEmpty() == true) {
                    binding?.noDataAvailable?.visibility = View.VISIBLE
                } else {
                    binding?.noDataAvailable?.visibility = View.GONE
                    it.data?.data?.let { it1 -> mAdapter.updateList(it1) }
                }
            }, 500)
        }

        viewModel.previousYearQuestionErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "previousYearQuestionErrorMessage: ")
            binding?.progressBarId?.visibility = View.GONE
            binding?.noDataAvailable?.visibility = View.VISIBLE
            binding?.noDataAvailable?.text = "Error Occurred"
        }

    }

    override fun clickEvent(item: PreviousYearQuestionResponse.Datum) {
        Log.d(TAG, "clickEvent: ")
        startActivity(

            // Use 'launchPdfFromPath' if you want to use assets file (enable "fromAssets" flag) / internal directory

            PdfViewerActivity.launchPdfFromUrl(           //PdfViewerActivity.Companion.launchPdfFromUrl(..   :: incase of JAVA
                context,
                item.file,                                // PDF URL in String format
                "Doc",                        // PDF Name/Title in String format
                "",                  // If nothing specific, Put "" it will save to Downloads
                enableDownload = false                    // This param is true by defualt.
            )
        )
    }

    override fun onPause() {
        super.onPause()
        binding?.progressBarId?.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        binding?.progressBarId?.visibility = View.GONE
    }
}