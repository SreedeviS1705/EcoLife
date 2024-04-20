package com.witsclassdevelopment.ui.signin.onBoarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.witsclassdevelopment.OnBoardActivity
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.ActivityOnBoardingSecondWelcomeBinding
import com.witsclassdevelopment.util.DataStoreManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardWelcomeSecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingSecondWelcomeBinding

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingSecondWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.redirectToLogin.setOnClickListener {
            val intent = Intent(this, OnBoardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        backActivityRedirect()
    }

    private fun backActivityRedirect() {
        val intent = Intent(this, OnBoardWelcomeFirstActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        finish()
    }
}