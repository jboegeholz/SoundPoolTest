package de.creatronix.soundpooltest

import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var mCSoundOne: Int = 0
    private lateinit var mSoundPool: SoundPool
    private var mLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSoundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d("SoundPoolTest", "Building Soundpool")
            SoundPool.Builder().setMaxStreams(1).build()
        } else {
            SoundPool(1, AudioManager.STREAM_MUSIC, 1)
        }
        mSoundPool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
            Log.d("SoundPoolTest", "Load Completed")
            mLoaded = true
        })
        Log.d("SoundPoolTest", "Loading wav")
        mCSoundOne = mSoundPool.load(applicationContext, R.raw.eins, 1)

    }

    fun play_one(view: View?) {
        Log.d("SoundPoolTest", "Button Clicked")
        if (mLoaded) {
            Log.d("SoundPoolTest", "Playing wav")
            mSoundPool.play(mCSoundOne, 1.0f, 1.0f, 0, 0, 1.0f)
        }
    }
}