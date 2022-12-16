package com.robosoft.foursquare.view.activity

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.robosoft.foursquare.adapter.ViewPagerAdapter
import com.robosoft.foursquare.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var homeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        tabLayout = homeBinding.tabLayout
        viewPager2 = homeBinding.viewPager2
        viewPager2.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager2) { tab, index ->
            tab.text = when (index) {
                0 -> "Near You"
                1 -> "Toppick"
                2 -> "Popular"
                3 -> "Lunch"
                4 -> "Coffee"
                else -> throw Resources.NotFoundException("Position Not Found")
            }
        }.attach()
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }
}