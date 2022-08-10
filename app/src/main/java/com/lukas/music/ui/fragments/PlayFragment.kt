package com.lukas.music.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lukas.music.databinding.FragmentPlayBinding
import com.lukas.music.instruments.Rhythm

class PlayFragment : Fragment() {
    lateinit var binding: FragmentPlayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayBinding.inflate(inflater)
        binding.playSwitch.setOnCheckedChangeListener { _, isOn ->
            Rhythm.on = isOn
        }
        return binding.root
    }
}