package com.lukas.music.ui.tab

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class TabListener(private val pager: ViewPager2): TabLayout.OnTabSelectedListener {
    override fun onTabSelected(tab: TabLayout.Tab?) {
        tab?.let { pager.currentItem = it.position }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }
}