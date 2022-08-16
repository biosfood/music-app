package com.lukas.music

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lukas.music.databinding.ActivityMainBinding
import com.lukas.music.instruments.Rhythm
import com.lukas.music.ui.fragments.CreditsFragment
import com.lukas.music.ui.fragments.InstrumentListFragment
import com.lukas.music.ui.fragments.PlayFragment
import com.lukas.music.ui.fragments.SongFragment
import com.lukas.music.ui.tab.PageListener
import com.lukas.music.ui.tab.TabAdapter
import com.lukas.music.ui.tab.TabListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tabFragments = listOf(
        PlayFragment(),
        SongFragment(),
        InstrumentListFragment(),
        CreditsFragment(),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tabPager.adapter = TabAdapter(supportFragmentManager, lifecycle, tabFragments)
        binding.tabPager.registerOnPageChangeCallback(PageListener(binding.tabLayout))
        binding.tabLayout.addOnTabSelectedListener(TabListener(binding.tabPager))
        startAudio()
        Rhythm.start()
    }

    companion object {
        external fun startAudio()

        init {
            System.loadLibrary("music")
        }
    }
}