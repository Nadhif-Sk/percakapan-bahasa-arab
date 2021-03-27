package com.example.percakapanbahasaarab

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_menu_percakapan.*
import kotlinx.android.synthetic.main.activity_percakapan_perkenalan.*

class PercakapanPerkenalan : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()
    private var pause: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_percakapan_perkenalan)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN )

        // Kodingan Tombol Back trus Di iringi mati audio
        mediaPlayer = MediaPlayer.create(applicationContext,R.raw.hiwar_perkenalan)
        btn_back_perkenalan.setOnClickListener {
            onBackPressed()
            if(mediaPlayer.isPlaying || pause.equals(true)) {
                pause = false
                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer.release()
                handler.removeCallbacks(runnable)
            }
        }

        //Mulai media Player
        btn_play_perkenalan.setOnClickListener{
            if(pause){
                mediaPlayer.seekTo(mediaPlayer.currentPosition)
                mediaPlayer.start()
                pause = false
                Toast.makeText(this,"media playing", Toast.LENGTH_SHORT).show()
            } else{
                mediaPlayer = MediaPlayer.create(applicationContext,R.raw.hiwar_perkenalan)
                mediaPlayer.start()
                Toast.makeText(this, "media playing", Toast.LENGTH_SHORT).show()
            }
            initializeSeekBar()
            btn_play_perkenalan.isEnabled = false
            btn_pause_perkenalan.isEnabled = true
            btn_stop_perkenalan.isEnabled = true

            mediaPlayer.setOnCompletionListener {
                btn_play_perkenalan.isEnabled = true
                btn_pause_perkenalan.isEnabled = false
                btn_stop_perkenalan.isEnabled = false
                Toast.makeText(this, "end", Toast.LENGTH_SHORT).show()
            }
        }

        // Pause Media Player
        btn_pause_perkenalan.setOnClickListener{
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
                pause = true
                btn_play_perkenalan.isEnabled = true
                btn_pause_perkenalan.isEnabled = false
                btn_stop_perkenalan.isEnabled = true
                Toast.makeText(this, "media pause", Toast.LENGTH_SHORT).show()
            }
        }

        //stop media player
        btn_stop_perkenalan.setOnClickListener{
            if(mediaPlayer.isPlaying || pause.equals(true)){
                pause = false
                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer.release()
                handler.removeCallbacks(runnable)

                btn_play_perkenalan.isEnabled = true
                btn_pause_perkenalan.isEnabled = false
                btn_stop_perkenalan.isEnabled = false
                Toast.makeText(this,"media stop", Toast.LENGTH_SHORT).show()
            }
        }

        // Seek bar change listener
        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (b) {
                    mediaPlayer.seekTo(i * 1000)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
    }

    // Method to initialize seek bar and audio stats
    private fun initializeSeekBar() {
        seek_bar.max = mediaPlayer.seconds

        runnable = Runnable {
            seek_bar.progress = mediaPlayer.currentSeconds

            tv_pass.text = "${mediaPlayer.currentSeconds} sec"
            val diff = mediaPlayer.seconds - mediaPlayer.currentSeconds
            tv_due.text = "$diff sec"

            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    // Creating an extension property to get the media player time duration in seconds
    val MediaPlayer.seconds: Int
        get() {
            return this.duration / 1000
        }
    // Creating an extension property to get media player current position in seconds
    val MediaPlayer.currentSeconds:Int
        get() {
            return this.currentPosition/1000
        }

}