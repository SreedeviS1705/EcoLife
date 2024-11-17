package com.welkinwits.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.welkinwits.ui.signin.onBoarding.OnBoardWelcomeFirstActivity

class SplashScreenActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Remove notification bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
       /* FirebaseMessaging.getInstance().subscribeToTopic("text_tutor_general")
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d("FirebaseMessaging", msg)
            }
*/
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed( {
            startActivity(
                Intent(
                    this@SplashScreenActivity,
                    OnBoardWelcomeFirstActivity::class.java
                )
            )
            finish()
        },2000)

    }
}