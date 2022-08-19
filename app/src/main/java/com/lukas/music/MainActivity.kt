/*
 * Copyright (C) 2022 Lukas Eisenhauer
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or(at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.lukas.music

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lukas.music.databinding.ActivityMainBinding
import com.lukas.music.ui.fragments.CreditsFragment
import com.lukas.music.ui.fragments.InstrumentListFragment
import com.lukas.music.ui.fragments.PlayFragment
import com.lukas.music.ui.fragments.SongFragment
import com.lukas.music.ui.tab.PageListener
import com.lukas.music.ui.tab.TabAdapter
import com.lukas.music.ui.tab.TabListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val playFragment = PlayFragment()
    private val tabFragments = listOf(
        playFragment,
        SongFragment(playFragment),
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
        supportActionBar?.hide()
    }

    companion object {
        external fun startAudio()

        init {
            System.loadLibrary("music")
        }
    }
}