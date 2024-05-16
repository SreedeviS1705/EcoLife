package com.welkinwits.ui.home.subject.material

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.rajat.pdfviewer.PdfViewerActivity
import com.welkinwits.R
import com.welkinwits.databinding.FragmentMaterialBinding
import com.welkinwits.service.respose.MaterialResponse
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MaterialFragment : BaseFragment(R.layout.fragment_material), IStudyMaterialListener {

    companion object {
        const val TAG = "MaterialFragment"
    }

    private val viewModel: StudyMaterialViewModel by viewModels()
    private var binding: FragmentMaterialBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMaterialBinding.bind(view)

        val chapterId = arguments?.getString("chapterId")
        val chapterName = arguments?.getString("chapterName")
        val subjectName = arguments?.getString("subjectName")
        binding?.chapterName?.text = chapterName

        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.please_wait)

        chapterId?.let {
            viewModel.getMaterials(chapterId)
        }


        val mAdapter = StudyMaterialAdapter(this)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.materialResponse.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.root?.isVisible = false
            it.data?.data?.let { it1 -> mAdapter.updateList(it1) }
        }

        viewModel.materialErrorMessage.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true
        }

        binding?.statusIndicatorLayout?.statusRetry?.setOnClickListener {
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = false
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = true
            binding?.statusIndicatorLayout?.statusText?.isVisible = false
            viewModel.getMaterials(chapterId)
        }

    }

    override fun clickEvent(subjectId: MaterialResponse.Data) {
        Log.d(TAG, "clickEvent: ")
        /*startActivity(
            PdfViewerActivity.launchPdfFromUrl(
                context,
                subjectId.path,
                subjectId.title,
                "",
                false
            )
        )*/
        /*binding?.subject?.visibility = View.GONE
        binding?.chapterName?.visibility = View.GONE
        binding?.recyclerView?.visibility = View.GONE
        binding?.webView?.visibility = View.VISIBLE
        binding?.webView?.webViewClient = WebViewClient()
        binding?.webView?.settings?.setSupportZoom(true)
        binding?.webView?.settings?.javaScriptEnabled = true
        binding?.webView?.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" +subjectId.path)
        binding?.webView?.settings?.builtInZoomControls = true;*/

        startActivity(

            // Use 'launchPdfFromPath' if you want to use assets file (enable "fromAssets" flag) / internal directory

            PdfViewerActivity.launchPdfFromUrl(           //PdfViewerActivity.Companion.launchPdfFromUrl(..   :: incase of JAVA
                context,
                subjectId.path,                                // PDF URL in String format
                "Doc",                        // PDF Name/Title in String format
                "",                  // If nothing specific, Put "" it will save to Downloads
                enableDownload = false                    // This param is true by defualt.
            )
        )
    }
}