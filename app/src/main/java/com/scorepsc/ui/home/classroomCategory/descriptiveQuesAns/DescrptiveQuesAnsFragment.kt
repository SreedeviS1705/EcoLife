package com.scorepsc.ui.home.classroomCategory.descriptiveQuesAns

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.rajat.pdfviewer.PdfViewerActivity
import com.scorepsc.R
import com.scorepsc.databinding.FragmentDescriptiveQuestAnsBinding
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescrptiveQuesAnsFragment : BaseFragment(R.layout.fragment_descriptive_quest_ans), IDescptQuesAnsPdfLoad {

    companion object {
        const val TAG = "DescrptiveQuesAnsFragment"
    }
    private var binding: FragmentDescriptiveQuestAnsBinding? = null
    private val viewModel: DescrpQuesAnsViewModel by viewModels()

    private lateinit var mAdapter: DescQuesAnsAdapter

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDescriptiveQuestAnsBinding.bind(view)
        val chapterId = arguments?.getString("chapterId")
        val chapterName = arguments?.getString("chapterName")
        //val subjectName = arguments?.getString("subjectName")
        binding?.description?.text = "Descriptive Question & Answer"

        mAdapter = activity?.let { DescQuesAnsAdapter(it, this) }!!
        binding?.classNotesRecyclerView?.setItemViewCacheSize(10)
        binding?.classNotesRecyclerView?.adapter = mAdapter

        chapterId?.let { viewModel.getDescrpQuesAns(it) }

        viewModel.descrptQuestAnsResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "chapterWiseStudyResponse size: "+it.data?.data?.size)
            if(it.data?.data?.isEmpty() == true) {
                binding?.noDescQuesAnsAvailableId?.visibility = View.VISIBLE
                binding?.noDescQuesAnsAvailableId?.text = "No Descriptive Question and Answer available"
            } else {
                binding?.noDescQuesAnsAvailableId?.visibility = View.GONE
            }
            it.data?.data?.let { it1 -> mAdapter.updateList(it1) }
        }

        viewModel.descrptQuestAnsErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "chapterWiseStudyErrorMessage: ")
            binding?.noDescQuesAnsAvailableId?.visibility = View.VISIBLE
            binding?.noDescQuesAnsAvailableId?.text = "Error loading the data"
        }

    }

    override fun loadPdf(path:String) {
        Log.d(TAG, "loadPdf path: $path")
        /*binding?.imageView8?.visibility = View.GONE
        binding?.description?.visibility = View.GONE
        binding?.classNotesRecyclerView?.visibility = View.GONE
        binding?.webView?.visibility = View.VISIBLE
        binding?.webView?.webViewClient = WebViewClient()
        binding?.webView?.settings?.setSupportZoom(true)
        binding?.webView?.settings?.javaScriptEnabled = true
        binding?.webView?.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" +path)
        binding?.webView?.settings?.builtInZoomControls = true;*/


        startActivity(

            // Use 'launchPdfFromPath' if you want to use assets file (enable "fromAssets" flag) / internal directory

            PdfViewerActivity.launchPdfFromUrl(           //PdfViewerActivity.Companion.launchPdfFromUrl(..   :: incase of JAVA
                context,
                path,                                // PDF URL in String format
                "Doc",                        // PDF Name/Title in String format
                "",                  // If nothing specific, Put "" it will save to Downloads
                enableDownload = false                    // This param is true by defualt.
            )
        )
    }
}