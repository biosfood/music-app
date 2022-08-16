package com.lukas.music.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukas.music.databinding.FragmentInstrumentListBinding
import com.lukas.music.ui.adapters.InstrumentAdapter

class InstrumentListFragment : Fragment() {
    lateinit var binding: FragmentInstrumentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInstrumentListBinding.inflate(inflater)
        binding.recyclerView.adapter = InstrumentAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        return binding.root
    }
}