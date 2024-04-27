package com.scorepsc

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.scorepsc.databinding.ActivityPlayerBinding
import vimeoextractor.OnVimeoExtractionListener
import vimeoextractor.VimeoExtractor
import vimeoextractor.VimeoVideo


class PlayerActivity : AppCompatActivity(), Player.Listener {


    private lateinit var binding: ActivityPlayerBinding
    private var player: SimpleExoPlayer? = null

    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0
    private var playerView: ExoPlayer?= null

    @SuppressLint("StaticFieldLeak")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }



        player = SimpleExoPlayer.Builder(this).build()
        binding.playerView.player = player

        val urlString = intent?.getStringExtra("URL")
        //val urlString = "https://www.youtube.com/watch?v=EzyXVfyx7CU"
        this.lifecycle.addObserver(binding.youtubePlayerView)

        if (urlString?.contains("youtube", true) == true) {
            Log.d("PlayerActivityURL", "YouTube: ")
            val split = urlString.split("v=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val firstSubString = split[0]
            val secondSubString = split[1]
            binding?.playerView?.visibility = View.GONE
            binding?.youtubePlayerView?.visibility = View.VISIBLE
            binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    Log.d("PlayerActivityURL", "YouTube: 2")
                    val videoId = secondSubString
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })

        } else if (urlString?.contains("Vimeo", true) == true) {
            binding?.youtubePlayerView?.visibility = View.GONE
            binding?.playerView?.visibility = View.VISIBLE
            if(urlString.indexOf("player") != -1) {
                Log.d("PlayerActivityTEST", "player: ")
                playerView = ExoPlayer.Builder(this).build()
                binding.playerView.player = playerView
                val mediaItem: MediaItem =
                    MediaItem.fromUri(urlString);

                playerView?.addMediaItem(mediaItem);
                playerView?.prepare();
                playerView?.playWhenReady = true

            } else {
                Log.d("PlayerActivityTEST", "not player: ")
                binding?.youtubePlayerView?.visibility = View.GONE
                binding?.playerView?.visibility = View.VISIBLE
                VimeoExtractor.getInstance()
                    .fetchVideoWithURL(urlString, null, object : OnVimeoExtractionListener {
                        override fun onSuccess(video: VimeoVideo) {
                            runOnUiThread {
                                if (video.hasStreams()) {
                                    video.streams.keys.maxOrNull()!!.let {
                                        video.streams[it]?.let {
                                            player?.setMediaItem(MediaItem.fromUri(it))
                                            initializePlayer()
                                        }
                                    }

                                }

                            }

                        }

                        override fun onFailure(throwable: Throwable?) {
                            //Error handling here
                        }
                    })
            }



        }

    }

    override fun onPlaybackStateChanged(state: Int) {
        super.onPlaybackStateChanged(state)
        when (state) {
            Player.STATE_IDLE -> {
                binding.playerView.keepScreenOn = false
            }
            Player.STATE_BUFFERING -> {
                binding.playerView.keepScreenOn = true
            }
            Player.STATE_READY -> {
                binding.playerView.keepScreenOn = true
            }
            Player.STATE_ENDED -> {
                binding.playerView.keepScreenOn = false
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    private fun initializePlayer() {
        player?.playWhenReady = playWhenReady
        player?.prepare()
        player?.addListener(this)
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < 24) {
            releasePlayer()
        }
        playerView?.stop()
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT >= 24) {
            releasePlayer()
        }
        playerView?.stop()
    }

    private fun releasePlayer() {
        if (player != null) {
            playWhenReady = player!!.playWhenReady
            playbackPosition = player!!.currentPosition
            currentWindow = player!!.currentWindowIndex
            player!!.release()
            player = null
        }
    }
}