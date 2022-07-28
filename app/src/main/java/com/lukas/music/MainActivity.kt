package com.lukas.music

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.lukas.music.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isOn = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sampleText.text = "test"
        startAudio()
    }

    public fun toggleAudio(view: View) {
        isOn = !isOn
        if (isOn) {
            unmuteAudio()
        } else {
            muteAudio()
        }
    }

    private external fun startAudio()
    private external fun muteAudio()
    private external fun unmuteAudio()

    companion object {
        init {
            System.loadLibrary("music")
        }
    }
}