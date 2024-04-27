package com.scorepsc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.scorepsc.ui.base.BaseActivity
import com.scorepsc.util.DataStoreManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardActivity : BaseActivity(R.layout.activity_onboard) {
    companion object {
        const val TAG = "OnBoardActivityScreen"
    }

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        GlobalScope.launch {
            dataStoreManager.setOnBoardingStatus(true)
            dataStoreManager.token.collect {
                it?.let {
                    Log.d(TAG, "GlobalScope.launch: token $it")
                    var intent = Intent(this@OnBoardActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onPause: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }
}