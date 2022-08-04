package com.lukas.music.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lukas.music.R
import com.lukas.music.databinding.FragmentInstrumentBinding

class InstrumentFragment() : Fragment(R.layout.fragment_instrument) {
    private lateinit var binding: FragmentInstrumentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInstrumentBinding.inflate(inflater)
        return binding.root
    }
}