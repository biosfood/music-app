package com.lukas.music

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.lukas.music.databinding.ActivityMainBinding
import com.lukas.music.ui.tab.TabAdapter
import com.lukas.music.ui.fragments.CreditsFragment
import com.lukas.music.ui.fragments.PlayFragment
import com.lukas.music.ui.tab.PageListener
import com.lukas.music.ui.tab.TabListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tabFragments = listOf<Fragment>(PlayFragment(), CreditsFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tabPager.adapter = TabAdapter(supportFragmentManager, lifecycle, tabFragments)
        binding.tabPager.registerOnPageChangeCallback(PageListener(binding.tabLayout))
        binding.tabLayout.addOnTabSelectedListener(TabListener(binding.tabPager))
        // startAudio()
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