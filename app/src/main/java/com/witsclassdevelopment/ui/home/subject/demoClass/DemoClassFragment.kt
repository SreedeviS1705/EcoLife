package com.witsclassdevelopment.ui.home.subject.demoClass

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.witsclassdevelopment.PlayerActivity
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentDemoClassBinding
import com.witsclassdevelopment.service.respose.homeBanner.demoClassSubject.DemoClassWithSubjectResponse
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoClassFragment : BaseFragment(R.layout.fragment_demo_class), IDemoClassSubjectListener {

    companion object {
        const val TAG = "DemoClassFragment"
    }

    private val viewModel: DemoClassSubjectViewModel by viewModels()
    private var binding: FragmentDemoClassBinding? = null
    private lateinit var mAdapter:DemoClassSubjectAdapter
    private var subjectId: String?= null


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDemoClassBinding.bind(view)

        subjectId = arguments?.getString("subjectId")
        subjectId?.toInt()?.let { viewModel.getDemoClassSubject(it) }

        mAdapter = DemoClassSubjectAdapter(this)
        binding?.recyclerView?.adapter = mAdapter
        showProgressBar(View.VISIBLE)

        viewModel.demoClassWithSubjectResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "demoClassWithSubjectResponse: size" + it.data?.data?.size)
            Handler(Looper.getMainLooper()).postDelayed({
                showProgressBar(View.GONE)
                mAdapter.updateList(it.data?.data)
            }, 1000)
        }

        viewModel.demoClassWithSubjectErrorMessage.observe(viewLifecycleOwner) {
            showProgressBar(View.GONE)
            binding?.errorMessage?.text = "Error loading the data"
        }

    }

    override fun clickEvent(subject: DemoClassWithSubjectResponse.HomeBannerData) {
        Log.d(TAG, "clickEvent subject: ${subject.title}")
        val intent = Intent(requireActivity(), PlayerActivity::class.java)
        intent.putExtra("URL", subject?.videoLink)
        startActivity(intent)
    }

    private fun showProgressBar(status:Int) {
        if(status == View.VISIBLE) {
            binding?.errorMessage?.visibility = View.GONE
            binding?.progressBarId?.visibility = View.VISIBLE
        } else {
            binding?.errorMessage?.visibility = View.VISIBLE
            binding?.progressBarId?.visibility = View.GONE
        }
    }

    override fun onStop() {
        super.onStop()
        showProgressBar(View.GONE)
    }
}