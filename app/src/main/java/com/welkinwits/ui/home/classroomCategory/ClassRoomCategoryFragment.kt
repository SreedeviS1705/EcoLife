package com.welkinwits.ui.home.classroomCategory

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.welkinwits.R
import com.welkinwits.databinding.FragmentClassRoomCategoryBinding
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClassRoomCategoryFragment : BaseFragment(R.layout.fragment_class_room_category) {

    companion object {
        const val TAG = "ClassRoomCategoryFragment"
    }
    private var binding: FragmentClassRoomCategoryBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentClassRoomCategoryBinding.bind(view)
        val chapterId = arguments?.getString("chapterId")
        val chapterName = arguments?.getString("chapterName")
        val subjectName = arguments?.getString("subjectName")
        binding?.description?.text = chapterName


        //Video Class
        binding?.videoClassCategory?.setOnClickListener {
            val data = Bundle().apply {
                putString("chapterId", chapterId)
                putString("chapterName", chapterName)
                putString("subjectName", subjectName)
            }
            navigate(R.id.topicFragment, data)
        }

        //Key Notes
        binding?.classNoteCategory?.setOnClickListener {
            val data = Bundle().apply {
                putString("chapterId", chapterId)
                putString("chapterName", chapterName)
                //putString("subjectName", subjectName)
            }
            navigate(R.id.chapterWiseStudyNotesFragment, data)
        }

        binding?.dissQuesAndAnswer?.setOnClickListener {
            val data = Bundle().apply {
                putString("chapterId", chapterId)
                putString("chapterName", chapterName)
                //putString("subjectName", subjectName)
            }
            navigate(R.id.descriptQuestionAnsFragment, data)
        }

        //Quiz
        binding?.questCategory?.setOnClickListener {
            val data = Bundle().apply {
                putString("chapterId", chapterId)
                putString("chapterName", chapterName)
                //putString("subjectName", subjectName)
            }
            navigate(R.id.classQuizFragment, data)
        }
    }
}