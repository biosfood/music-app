package com.lukas.music

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import androidx.fragment.app.Fragment
import com.lukas.music.databinding.ActivityMainBinding
import com.lukas.music.ui.tab.TabAdapter
import com.lukas.music.ui.fragments.CreditsFragment
import com.lukas.music.ui.fragments.InstrumentListFragment
import com.lukas.music.ui.fragments.PlayFragment
import com.lukas.music.ui.tab.PageListener
import com.lukas.music.ui.tab.TabListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tabFragments = listOf(PlayFragment(), InstrumentListFragment(), CreditsFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tabPager.adapter = TabAdapter(supportFragmentManager, lifecycle, tabFragments)
        binding.tabPager.registerOnPageChangeCallback(PageListener(binding.tabLayout))
        binding.tabLayout.addOnTabSelectedListener(TabListener(binding.tabPager))
        startAudio()
        // muteAudio()
    }

    companion object {
        external fun startAudio()
        external fun muteAudio()
        external fun unmuteAudio()

        init {
            System.loadLibrary("music")
        }
    }
}