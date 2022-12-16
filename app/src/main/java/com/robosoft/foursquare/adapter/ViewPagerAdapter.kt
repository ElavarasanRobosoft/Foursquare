package com.robosoft.foursquare.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.robosoft.foursquare.view.fragment.home.*

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 5
    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> NearYouFragment()
            1 -> ToppickFragment()
            2 -> PopularFragment()
            3 -> LunchFragment()
            4 -> CoffeeFragment()
            else -> throw Resources.NotFoundException("Position Not Found")
        }
    }
}