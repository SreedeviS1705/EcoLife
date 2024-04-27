package com.scorepsc

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.scorepsc.databinding.ActivityImagezoomImgBinding


class ImageZoomActivity: AppCompatActivity() {
    companion object {
        const val TAG = "ImageZoomActivityClass"
    }
    private lateinit var binding: ActivityImagezoomImgBinding
    public var mScaleFactor = 1.0f
    private var mScaleGestureDetector: ScaleGestureDetector? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        binding = ActivityImagezoomImgBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var imageUrl = intent.extras?.getString("image_url")
        Picasso.get()
            .load(imageUrl)
            .into(binding.zoomImageId)
        mScaleGestureDetector = ScaleGestureDetector(this, ScaleListener())

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return event?.let { mScaleGestureDetector?.onTouchEvent(it) } == true;
    }

    private inner class ScaleListener : SimpleOnScaleGestureListener() {
        // when a scale gesture is detected, use it to resize the image
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            binding.zoomImageId?.scaleX = mScaleFactor
            binding.zoomImageId?.scaleY = mScaleFactor
            return true
        }
    }
}