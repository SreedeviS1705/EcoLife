package com.welkinwits.ui.chapter

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentChapterBinding
import com.welkinwits.service.respose.ChapterResponse
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChapterFragment : BaseFragment(R.layout.fragment_chapter),IChapter {
    companion object {
        const val TAG = "ChapterFragment"
    }

    private val viewModel: ChapterViewModel by viewModels()
    private var binding: FragmentChapterBinding? = null
    private var redirectType: String? = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChapterBinding.bind(view)

        val subId = arguments?.getString("subID")
        val subName = arguments?.getString("subName")
        redirectType = arguments?.getString("redirectType")

        binding?.description?.text = subName

        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.fetching_chapters)
        viewModel.getChapters(subId)

        val mAdapter = ChaptersAdapter(this,context,redirectType)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.chapterResponse.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.root?.isVisible = false
            it.data?.data?.let { it1 -> mAdapter.updateList(it1) }
        }

        viewModel.chapterErrorMessage.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true
        }

        binding?.statusIndicatorLayout?.statusRetry?.setOnClickListener {
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = false
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = true
            binding?.statusIndicatorLayout?.statusText?.isVisible = false
            viewModel.getChapters(subId)
        }

    }

    override fun clickEvent(data: ChapterResponse.ChapterData) {
        Log.d(TAG, "clickEvent: "+redirectType)
        if(redirectType.equals("recordedClasses")) {
            /*val data = Bundle().apply {
                putString("chapterId", data.chapterId)
                putString("chapterName", data.chapterName)
                putString("subjectName", data.subjectName)
            }
            navigate(R.id.classRoomCategoryFragment, data)*/
            val data = Bundle().apply {
                putString("chapterId", data.chapterId)
                putString("chapterName", data.chapterName)
                putString("subjectName", data.subjectName)
            }
            navigate(R.id.topicFragment, data)
        } else if(redirectType.equals("studyMaterials")) {
            val data = Bundle().apply {
                putString("chapterId", data.chapterId)
                putString("chapterName", data.chapterName)
                putString("subjectName", data.subjectName)
            }
            navigate(R.id.studyMaterialFragment,data)
        }

    }
}