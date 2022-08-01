package com.lukas.music.ui.tab

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class PageListener(private val tabLayout: TabLayout): ViewPager2.OnPageChangeCallback() {
    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        tabLayout.selectTab(tabLayout.getTabAt(position))
    }
}