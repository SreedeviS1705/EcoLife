package com.welkinwits.ui.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.welkinwits.OnBoardActivity
import com.welkinwits.R
import com.welkinwits.adapter.NewsAdapter
import com.welkinwits.databinding.FragmentHomeBinding
import com.welkinwits.service.respose.homeBanner.ImageBanner
import com.welkinwits.service.respose.homeBanner.latestUpdates.LatestUpdateResponse
import com.welkinwits.ui.base.BaseFragment
import com.welkinwits.util.DataStoreManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home),IHomeListing , IScrollClick{

    companion object{
        const val TAG = "HomeFragment"
    }

    private val homeViewModel: HomeViewModel by activityViewModels()
    private var binding: FragmentHomeBinding? = null

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    private lateinit var futurePlusAdapter: FuturePlusSliderAdapter
    private var mHandler: Handler ?= null
    private var subscriptionStatus: String?= "active"

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        //Slider adapter set
        futurePlusAdapter = FuturePlusSliderAdapter(this)
        binding?.imageSlider?.adapter = futurePlusAdapter



        val rootView = binding?.bottomSheet?.rootView
        val bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout> =
            BottomSheetBehavior.from(rootView!!)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        /*binding?.bottomSheet?.close?.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }*/
        /*binding?.bottomSheet?.bottomSheetHome?.setOnClickListener {
            //Home Click
        }
        binding?.bottomSheet?.bottomSheetHistory?.setOnClickListener {
            //Home Click
        }
        binding?.bottomSheet?.bottomSheetProfile?.setOnClickListener {
            navigate(R.id.navigation_profile)
        }
*/
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (bottomSheetBehavior?.state == BottomSheetBehavior.STATE_EXPANDED) {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    } else {
                        requireActivity().finish()
                    }
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)



        homeViewModel.homeBannerResponse.observe(viewLifecycleOwner) {
            val bannerData : ArrayList<ImageBanner>? = it.data?.data?.imageBanners
            Log.d(TAG, "homeBannerResponse: bannerData size "+bannerData?.size)
            futurePlusAdapter.updateList(bannerData)
        }

        homeViewModel.errorMessage.observe(viewLifecycleOwner) {
            if (it?.contains("Unauthorized") == true) {
                showAlert(
                    title = "Invalid session",
                    message = it.split(":")[1],
                    cancellable = false,
                    positiveButtonClick = { dialog, which ->
                        dialog.dismiss()

                        GlobalScope.launch { dataStoreManager.clear() }
                        startActivity(Intent(requireContext(), OnBoardActivity::class.java))
                        requireActivity().finish()
                    })

            }
        }

//        homeViewModel.newsResponse.observe(viewLifecycleOwner) {
//            it.data?.data?.let(newsAdapter::submitList)
//        }

        //Examination
//        binding?.ExaminationId?.setOnClickListener {
//            navigate(R.id.examFragment)
//
//           /* *//**
//             * For The apps those need Subscription option
//             *//*
//            if(subscriptionStatus?.compareTo("active") == 0) {
//                navigate(R.id.examFragment)
//            } else {
//                context?.let { it1 -> showAlertDialogButtonClicked(it1) }
//            }*/
//        }

        binding?.cardView7?.setOnClickListener {
            navigate(R.id.navigation_support)
        }

        binding?.wishHeading?.text = homeViewModel.getWishMessage()

         homeViewModel.getUserName.observe(viewLifecycleOwner){
             binding?.wishUser?.text = it
         }

        /*binding?.entrancePracticeId?.setOnClickListener {
            if(subscriptionStatus?.compareTo("active") == 0) {
                navigate(R.id.enteranceExamQuestionTypeFragment)
            } else {
                context?.let { it1 -> showAlertDialogButtonClicked(it1) }
            }
        }*/

        /*binding?.studyNotesId?.setOnClickListener {
            if(subscriptionStatus?.compareTo("active") == 0) {
                navigate(R.id.fragmentProbableQAGroup)
            } else {
                context?.let { it1 -> showAlertDialogButtonClicked(it1) }
            }
        }*/

        /*binding?.descriptiveQA?.setOnClickListener {
            if(subscriptionStatus?.compareTo("active") == 0) {
                navigate(R.id.descriptQuestionAnsFragment)
            } else {
                context?.let { it1 -> showAlertDialogButtonClicked(it1) }
            }
        }
*/
        /*binding?.importantLinksId?.setOnClickListener {
            navigate(R.id.fragmentImportantLinks)
        }*/

        binding?.previousQuestionPaper?.setOnClickListener {
            navigate(R.id.fragmentPreviousyearQuestionPaper)
        }

        binding?.cardView8?.setOnClickListener {
            navigate(R.id.navigation_analytics)
        }

        /*binding?.exams?.setOnClickListener {
            if(subscriptionStatus?.compareTo("ACTIVE") == 0) {
                navigate(R.id.examFragment)
            } else {
                Toast.makeText(activity, "Subscription not available", Toast.LENGTH_SHORT).show()
            }
        }*/

        //Live Class
        binding?.liveClassContainer?.setOnClickListener {
            val bundle = Bundle()
            //bundle.putString("demoClass", "demoClass")
            navigate(R.id.subjectFragment, bundle)
        }

        //OffLine Class
        binding?.offLineTrainingIdLayout?.setOnClickListener {
            val bundle = Bundle()
        //    bundle.putString("demoClass", "demoClass")
            navigate(R.id.offLineTrainingFragment, bundle)
        }

        //Gallery
        binding?.galleryIdLayout?.setOnClickListener {
            val bundle = Bundle()
            //    bundle.putString("demoClass", "demoClass")
            navigate(R.id.galleryFragment, bundle)
        }

        //Library
//        binding?.probableQuestionsId?.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString("redirectType","studyMaterials")
//            navigate(R.id.recordedClassesSubjectFragment, bundle)
//
//        /*    // Subscription option
//            if(subscriptionStatus?.compareTo("active") == 0) {
//                val bundle = Bundle()
//                bundle.putString("redirectType","studyMaterials")
//                navigate(R.id.recordedClassesSubjectFragment, bundle)
//            } else {
//                context?.let { it1 -> showAlertDialogButtonClicked(it1) }
//            }
//*/
//        }

        //Recorded Class
        binding?.classRooms?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("redirectType","recordedClasses")
            navigate(R.id.recordedClassesSubjectFragment, bundle)

           /* *//**
             * For The apps those need Subscription option
             *//*
            if(subscriptionStatus?.compareTo("active") == 0) {
                val bundle = Bundle()
                bundle.putString("redirectType","recordedClasses")
                navigate(R.id.recordedClassesSubjectFragment, bundle)
            } else {
                context?.let { it1 -> showAlertDialogButtonClicked(it1) }
            }*/
        }

        binding?.cardView6?.setOnClickListener {
            navigate(R.id.allSubscriptionListFragment)
        }

        /*binding?.scholershipId?.setOnClickListener {
            *//*if(subscriptionStatus?.compareTo("active") == 0) {
                navigate(R.id.scholarshipExamsListFragment)
            } else {
                context?.let { it1 -> showAlertDialogButtonClicked(it1) }
            }*//*
            navigate(R.id.scholarshipExamsListFragment)
        }*/

        binding?.offerContainer?.setOnClickListener {
            /*val bundle = Bundle()
            bundle.putString("demoClass", "demoClass")
            navigate(R.id.subjectFragment, bundle)*/
            navigate(R.id.demoVideoFragment)
        }

        //Support
//        binding?.askDoubtId?.setOnClickListener {
//            navigate(R.id.navigation_help)
//        }
//
//        binding?.faqLayout?.setOnClickListener {
//            navigate(R.id.navigation_faq)
//        }
        /*binding?.currentAffairsId?.setOnClickListener {
            navigate(R.id.currentAffairsFragment)
        }*/

        /*binding?.didYouKnowWhyCard?.setOnClickListener {
            if(subscriptionStatus?.compareTo("active") == 0) {
                navigate(R.id.fragmentFlashCardGroup)
            } else {
                context?.let { it1 -> showAlertDialogButtonClicked(it1) }
            }
        }*/

        /*binding?.jobUpdated?.setOnClickListener {
            navigate(R.id.navigation_help)
        }*/

        /*binding?.demoClass?.setOnClickListener {
            navigate(R.id.demoVideoFragment)
        }*/

        /*binding?.studyMaterials?.setOnClickListener {
            if(subscriptionStatus?.compareTo("ACTIVE") == 0) {
                val bundle = Bundle()
                bundle.putString("redirectType","studyMaterials")
                navigate(R.id.recordedClassesSubjectFragment, bundle)
            } else {
                Toast.makeText(activity, "Subscription not available", Toast.LENGTH_SHORT).show()
            }

        }*/

        /*binding?.feePayment?.setOnClickListener {
            navigate(R.id.fee_payment)
        }*/

        //News Updates
        binding?.newAndEvents?.setOnClickListener {
            navigate(R.id.newsEventFragment)
        }

        /*binding?.jobUpdatesId?.setOnClickListener {
            navigate(R.id.jobUpdateFragment)
        }*/
        /*binding?.analyticsId?.setOnClickListener {
            navigate(R.id.navigation_analytics)
        }*/
        /*binding?.paymentHistoryId?.setOnClickListener {
            navigate(R.id.payment_history)
        }*/
        //Profile
//        binding?.profileHomeId?.setOnClickListener {
//            navigate(R.id.navigation_profile)
//        }
        //Ask Doubt
        binding?.supportHomeId?.setOnClickListener {
            navigate(R.id.navigation_support)
        }
        /*binding?.subscriptionTenureSystemId?.setOnClickListener {
            navigate(R.id.activeSubscriptionFragment)
        }*/
        /*binding?.switchAcadamicStreamId?.setOnClickListener {
            navigate(R.id.academicLevelsFragment)
        }*/

        /*binding?.analyticsCard?.setOnClickListener {
            if(subscriptionStatus?.compareTo("ACTIVE") == 0) {
                navigate(R.id.navigation_analytics)
            } else {
                Toast.makeText(activity, "Subscription not available", Toast.LENGTH_SHORT).show()
            }
        }*/

        val mUpdateAdapter = LatestUpdatesAdapter(activity, this)
        val mManger = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL ,false)
        binding?.updateRecyclerview?.layoutManager = mManger
        binding?.updateRecyclerview?.adapter = mUpdateAdapter

        binding?.updateRecyclerview?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstItemVisible: Int = mManger.findFirstVisibleItemPosition()
                if (firstItemVisible != 0 && firstItemVisible % Int.MAX_VALUE === 0) {
                    recyclerView.layoutManager!!.scrollToPosition(0)
                }
            }
        })


        mHandler = Handler(Looper.getMainLooper())
        val runnable: Runnable = object : Runnable {
            override fun run() {
                binding?.updateRecyclerview?.scrollBy(1, 0)
                mHandler?.postDelayed(this, 0)
            }
        }
        mHandler?.postDelayed(runnable, 0)

        homeViewModel.latestUpdateResponse.observe(viewLifecycleOwner) {
            it.data?.data?.let { it1 -> mUpdateAdapter.updateList(it1) }
        }

        homeViewModel.getActiveSubscriptionResponse.observe(viewLifecycleOwner) {
            binding?.homeContainer?.visibility = View.VISIBLE
            binding?.homeProgressContainer?.visibility = View.GONE
            subscriptionStatus = it.data?.data?.subscriptionStatus
            //subscriptionStatus = "activea"
            Log.d(TAG, "getActiveSubscriptionResponse: $subscriptionStatus")
            if (subscriptionStatus?.equals("active") == true) {
                //binding?.classRoomLockIc?.visibility = View.GONE
                //binding?.probableAQLockIc?.visibility = View.GONE
                //binding?.studyNotessLockIc?.visibility = View.GONE
                //binding?.didYouKnowLockIc?.visibility = View.GONE
            } else {
                //binding?.classRoomLockIc?.visibility = View.VISIBLE
                //binding?.probableAQLockIc?.visibility = View.VISIBLE
                //binding?.studyNotessLockIc?.visibility = View.VISIBLE
                //binding?.didYouKnowLockIc?.visibility = View.VISIBLE
                /*binding?.classRooms?.alpha = 0.4f
                binding?.studyNotesId?.alpha = 0.4f
                binding?.probableQuestionsId?.alpha = 0.4f
                binding?.didYouKnowWhyCard?.alpha = 0.4f
                binding?.ExaminationId?.alpha = 0.4f
                binding?.entrancePracticeId?.alpha = 0.4f*/
            }
        }

        homeViewModel.getActiveSubscriptionerrorMessage.observe(viewLifecycleOwner){
            binding?.homeContainer?.visibility = View.VISIBLE
            binding?.homeProgressContainer?.visibility = View.GONE
            Log.d(TAG, "getActiveSubscriptionerrorMessage: ")
        }


    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")

        homeViewModel.getSubjects()
        homeViewModel.getNews()
        homeViewModel.getHomeBanner()
        homeViewModel.getScrollUpdates()
        homeViewModel.getActiveSubscription()
        homeViewModel.getUserName()

        binding?.homeContainer?.visibility = View.GONE
        binding?.homeProgressContainer?.visibility = View.VISIBLE
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
        binding?.homeContainer?.visibility = View.VISIBLE
        binding?.homeProgressContainer?.visibility = View.GONE
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        mHandler =  null
    }

    override fun clickSliderItem(url: String) {
        try {
            Log.d(TAG, "clickSliderItem:  $url")
            val openURL = Intent(Intent.ACTION_VIEW,Uri.parse(url))
          //  openURL.data = Uri.parse(url)
            startActivity(openURL)
        }catch (e : ActivityNotFoundException){
            Log.e(TAG, "No activity found to handle the intent")

        }

    }

    override fun clickEvent(obj: LatestUpdateResponse.Datum?) {
        Log.d(TAG, "clickEvent:  $id")
        val bundle = Bundle()
        bundle.putString("batchId",obj?.id)
        bundle.putString("batchName",obj?.title)
        navigate(R.id.liveClassesFragment, bundle)
    }

    private fun showAlertDialogButtonClicked(mContext: Context) {
        val dialog = Dialog(mContext)
        dialog.setContentView(R.layout.subscription_status_dialogue)

        val confirm = dialog.findViewById(R.id.button2) as Button

        confirm.setOnClickListener {
            navigate(R.id.allSubscriptionListFragment)
            dialog.hide()
        }

        dialog.show()
    }
}