package com.robosoft.foursquare.view.activity

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.robosoft.foursquare.R
import com.robosoft.foursquare.adapter.ViewPagerAdapter
import com.robosoft.foursquare.databinding.ActivityHomeBinding
import com.robosoft.foursquare.view.fragment.RegistrationFragment
import com.robosoft.foursquare.view.fragment.navigationmenu.FavouriteFragment
import kotlinx.android.synthetic.main.activity_home.*
import java.nio.channels.AsynchronousFileChannel.open

class HomeActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var homeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        drawerLayout = homeBinding.drawerLayout
        navigationView = homeBinding.navView

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        homeBinding.toolbar.menu.setOnClickListener {
            drawerLayout.open()
        }

//        navigationView.setNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.nav_favourites -> {
//                    startActivity(Intent(this,))
//                }
//                R.id.nav_feedback -> {}
//                R.id.nav_about_us -> {}
//                R.id.nav_logout -> {}
//            }
//        }
//
//
//        override fun onOptionsItemSelected(item: MenuItem): Boolean {
//            if (this.toggle.onOptionsItemSelected(item)) {
//                return true
//            }
//            return false
//        }

        tabLayout = homeBinding.tabLayout
        viewPager2 = homeBinding.viewPager2
        viewPager2.isUserInputEnabled = false
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