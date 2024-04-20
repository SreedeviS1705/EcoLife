package com.witsclassdevelopment.ui.home.classroomCategory

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.rajat.pdfviewer.PdfViewerActivity
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentChapterWiseStudyNotesBinding
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChapterWiseStudyNotesFragment : BaseFragment(R.layout.fragment_chapter_wise_study_notes), IPdfLoad {

    companion object {
        const val TAG = "ChapterStudyNotes"
    }
    private var binding: FragmentChapterWiseStudyNotesBinding? = null
    private val viewModel: ChapterWiseStudyNotesViewModel by viewModels()

    private lateinit var mAdapter: ClassNotesAdapter

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChapterWiseStudyNotesBinding.bind(view)
        val chapterId = arguments?.getString("chapterId")
        val chapterName = arguments?.getString("chapterName")
        //val subjectName = arguments?.getString("subjectName")
        binding?.description?.text = "$chapterName"

        mAdapter = activity?.let { ClassNotesAdapter(it, this) }!!
        binding?.classNotesRecyclerView?.setItemViewCacheSize(10)
        binding?.classNotesRecyclerView?.adapter = mAdapter

        chapterId?.let { viewModel.getChapterWiseStudyNotes(it) }

        viewModel.chapterWiseStudyResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "chapterWiseStudyResponse size: "+it.data?.data?.size)
            if(it.data?.data?.isEmpty() == true) {
                binding?.noDescQuesAnsAvailableId?.visibility = View.VISIBLE
                binding?.noDescQuesAnsAvailableId?.text = "No Class Notes available"
            } else {
                binding?.noDescQuesAnsAvailableId?.visibility = View.GONE
            }

            it.data?.data?.let { it1 -> mAdapter.updateList(it1) }
        }

        viewModel.chapterWiseStudyErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "chapterWiseStudyErrorMessage: ")

            binding?.noDescQuesAnsAvailableId?.visibility = View.VISIBLE
            binding?.noDescQuesAnsAvailableId?.text = "Error loading the data"
        }

    }

    override fun loadPdf(path:String) {
        Log.d(TAG, "loadPdf path: $path")
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