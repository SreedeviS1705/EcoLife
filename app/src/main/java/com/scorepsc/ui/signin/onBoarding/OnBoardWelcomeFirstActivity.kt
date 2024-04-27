package com.scorepsc.ui.signin.onBoarding

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.scorepsc.OnBoardActivity
import com.scorepsc.R
import com.scorepsc.databinding.ActivityOnBoardingStartWelcomeBinding
import com.scorepsc.util.DataStoreManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardWelcomeFirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingStartWelcomeBinding

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission is granted. Continue the action or workflow in your
            // app.
           // sendNotification(this)
            Log.d("TTPermissionCheck", "PERMISSION GRANTED")
        } else {
            // Explain to the user that the feature is unavailable because the
            // features requires a permission that the user has denied. At the
            // same time, respect the user's decision. Don't link to system
            // settings in an effort to convince the user to change their
            // decision.
            Log.d("TTPermissionCheck", "PERMISSION NOT GRANTED")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(
                Manifest.permission.POST_NOTIFICATIONS
            )
        } else {
            Log.d("TTPermissionCheck", "NOT NEEDED")
        }

        GlobalScope.launch(Dispatchers.Main) {
            dataStoreManager.onBoardingStatus.collect {
                if(it == true) {
                    startActivity(Intent(this@OnBoardWelcomeFirstActivity, OnBoardActivity::class.java))
                    finish()
                } else {
                    binding = ActivityOnBoardingStartWelcomeBinding.inflate(layoutInflater)
                    setContentView(binding.root)
                    binding.redirectToOnBoardingSecond.setOnClickListener {
                        startActivity(Intent(this@OnBoardWelcomeFirstActivity, OnBoardWelcomeSecondActivity::class.java))
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        finish()
                    }
                }
            }
        }

    }
}