package com.welkinwits.ui.home.accadamicStreamSwitch

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.welkinwits.R
import com.welkinwits.databinding.FragmentAcadamicStreamSwitchBinding
import com.welkinwits.service.respose.homeBanner.acadamicStreamSwitch.acadamicLevels.GetAcadamicLevelsResponse
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AcadamicStreamSwitchFragment : BaseFragment(R.layout.fragment_acadamic_stream_switch), IAcademicStreamSwitch {

    companion object {
        const val TAG = "AcadamicStream"
    }

    private val viewModel: AcademicLevelsViewModel by viewModels()
    private var binding: FragmentAcadamicStreamSwitchBinding? = null
    private var mAdapter: AcademicLevelsAdapter ?= null
    private var acId: String? = ""


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAcadamicStreamSwitchBinding.bind(view)

        viewModel.getAcademicLevels()


        mAdapter = AcademicLevelsAdapter(this)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.getAcadamicLevelsResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "examResponse: size" + it.data?.data?.size)
            mAdapter?.updateList(it.data?.data)
        }

        viewModel.getAcadamicLevelsErrorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(activity,"error loading the data", Toast.LENGTH_LONG).show()
        }

        viewModel.changeAcadamicLevelResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "examResponse: size" + it.data?.data?.size)
            Toast.makeText(activity,""+it.data?.message, Toast.LENGTH_LONG).show()
            //navigate(R.id.academicStreamSuccessFragment)
            var action = AcadamicStreamSwitchFragmentDirections.actionAcademicLevelsFragmentToAcademicStreamSuccessFragment()
            action?.let { it1 -> findNavController().navigate(it1) }
        }

        viewModel.changeAcadamicLevelErrorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(activity,"error changing the stream", Toast.LENGTH_LONG).show()
        }

        binding?.changeStreamBtn?.setOnClickListener {
            acId?.toInt()?.let { viewModel.changeAcademicLevels(it) }
        }

    }

    override fun clickEvent(item: GetAcadamicLevelsResponse.Datum) {
        Log.d(TAG, "clickEvent ")
        acId = item.acId
    }

    /*    override fun clickEvent(data: SubjectResponse.SubjectData) {
            Log.d(TAG, "clickEvent: ")
            val data = Bundle().apply {
                putString("subID", data.subjectId)
                putString("subName", data.subjectName)
                putString("redirectType",redirectType)
            }
            navigate(R.id.chapterFragment, data)
        }*/
}