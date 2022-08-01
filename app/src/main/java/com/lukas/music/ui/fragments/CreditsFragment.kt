package com.lukas.music.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukas.music.R

class CreditsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_credits, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CreditsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}