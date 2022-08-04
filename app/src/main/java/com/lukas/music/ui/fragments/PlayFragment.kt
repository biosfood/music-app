package com.lukas.music.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukas.music.MainActivity
import com.lukas.music.R
import com.lukas.music.databinding.FragmentPlayBinding

class PlayFragment : Fragment() {
    lateinit var binding: FragmentPlayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayBinding.inflate(inflater)
        binding.playSwitch.setOnCheckedChangeListener { _, isOn ->
            if (isOn) {
                MainActivity.unmuteAudio()
            } else {
                MainActivity.muteAudio()
            }
        }
        return binding.root
    }
}