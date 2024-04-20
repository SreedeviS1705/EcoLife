package com.witsclassdevelopment.ui.home.classroomCategory.quiz.quizExam

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentClassSingleQuizBinding
import com.witsclassdevelopment.model.QuestionModelData
import com.witsclassdevelopment.service.request.quiz.PostQuizAnswerRequest
import com.witsclassdevelopment.service.request.quiz.SubmitDatum
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ClassSingleQuizFragment : BaseFragment(R.layout.fragment_class_single_quiz) {

    companion object {
        const val TAG = "ClassSingleQuizFragment"
    }
    private var binding: FragmentClassSingleQuizBinding? = null
    private val viewModel: ClassQuizSingleViewModel by viewModels()
    private lateinit var mAdapter: ClassQuizQuestionAdapter
    private var quizId: String? = ""
    private var chapterId: String? = ""


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentClassSingleQuizBinding.bind(view)
        quizId = arguments?.getString("quiz_id")
        chapterId = arguments?.getString("chapterId")

        quizId?.let { viewModel.getQuizSingleExamList(quizId!!) }

        mAdapter = activity?.let { ClassQuizQuestionAdapter(it) }!!
        binding?.quizQuestionRecyclerview?.adapter = mAdapter

        viewModel.getQuizSingleQuestionsResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "getQuizSingleQuestionsResponse size: ")
            if(it.data?.data?.questions?.isEmpty() == true) {
                binding?.noQuizQuestionAvailableId?.visibility = View.VISIBLE
                binding?.textView58?.visibility = View.GONE
                binding?.textView59?.visibility = View.GONE
                binding?.totalMarkTextId?.visibility = View.GONE
                binding?.submitBtn?.visibility = View.GONE
                binding?.noQuizQuestionAvailableId?.text = "No Quiz available"
            } else {
                binding?.totalMarkTextId?.text = "Total Marks : "+it.data?.data?.quiz?.maxMarks
                binding?.noQuizQuestionAvailableId?.visibility = View.GONE
                binding?.textView58?.visibility = View.VISIBLE
                binding?.textView59?.visibility = View.VISIBLE
                binding?.totalMarkTextId?.visibility = View.VISIBLE
                binding?.submitBtn?.visibility = View.VISIBLE
                binding?.textView59?.text =it.data?.data?.quiz?.name
            }
            Log.d(TAG, "getQuizSingleQuestionsResponse size: "+it.data?.data?.questions?.size)
            val questions = it.data?.data?.questions
            for (item in questions!!) {
                val optionsList: ArrayList<QuestionModelData>? = ArrayList<QuestionModelData>()
                for (innerItem in item.options!!) {
                    val model = QuestionModelData(false, innerItem, item?.questionId?.toInt(), true)
                    optionsList?.add(model)
                }
                item.optionsList = optionsList
            }

            questions.let { it1 -> mAdapter.updateList(questions) }
        }

        viewModel.getQuizSingleQuestionsErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "getQuizSingleQuestionsErrorMessage: ")
            binding?.textView58?.visibility = View.GONE
            binding?.textView59?.visibility = View.GONE
            binding?.totalMarkTextId?.visibility = View.GONE
            binding?.submitBtn?.visibility = View.GONE
            binding?.noQuizQuestionAvailableId?.visibility = View.VISIBLE
            binding?.noQuizQuestionAvailableId?.text = "Error loading the data"
        }

        viewModel.getPostQuizAnswerResponse.observe(viewLifecycleOwner){
            Log.d(TAG, "getPostQuizAnswerResponse: ")
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed(Runnable {
                binding?.loader?.visibility = View.GONE
                val direction = it.data?.data?.message?.let { it1 ->
                    it.data?.data?.result?.let { it2 ->
                        it.data?.data?.scoreText?.let { it3 ->
                            chapterId?.let { it4 ->
                                ClassSingleQuizFragmentDirections.actionQuizListFragmentToSuccessFragment(
                                    it1, it3, it2, it4
                                )
                            }
                        }
                    }
                }
                direction?.let { it1 -> findNavController().navigate(it1) }
            }, 2000)

        }

        viewModel.getPostQuizAnswerErrorMessage.observe(viewLifecycleOwner){
            Log.d(TAG, "getPostQuizAnswerErrorMessage: ")
            binding?.loader?.visibility = View.GONE
            Toast.makeText(activity,"Error", Toast.LENGTH_SHORT).show()
        }

        binding?.submitBtn?.setOnClickListener {
            Log.d(TAG,"submitBtn clicked"
            )
            showAlertDialogButtonClicked(requireActivity())
        }

    }

    private fun showAlertDialogButtonClicked(mContext:Context) {
        val dialog = Dialog(mContext)
        dialog.setContentView(R.layout.quiz_submit_dialogue)

        val confirm = dialog.findViewById(R.id.button2) as Button
        val goBack = dialog.findViewById(R.id.button) as Button

        confirm.setOnClickListener {
            binding?.loader?.visibility = View.VISIBLE
            //binding?.constraintLayout11?.visibility = View.GONE
            val mList = ArrayList<SubmitDatum>()
            if(mAdapter?.getList() != null) {
                for(item in mAdapter?.getList()!!) {
                    if(item.optionsList != null) {
                        for(innerItem in item.optionsList!!) {
                            if(innerItem.mSelectedStatus) {
                                val data = SubmitDatum()
                                data.questionId = innerItem.mQuestionId
                                data.answer = innerItem.mQuestionString
                                mList.add(data)
                            }
                        }
                    }

                }
            }
            Log.d(TAG,"submitBtn clicked"+mList)
            viewModel.postQuizAnswerSingle(PostQuizAnswerRequest("",quizId?.toInt(),mList))
            dialog.hide()
        }

        goBack.setOnClickListener {
            dialog.hide()
        }

        dialog.show()
    }
}