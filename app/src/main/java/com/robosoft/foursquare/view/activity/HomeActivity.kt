package com.robosoft.foursquare.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.Location
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.robosoft.foursquare.R
import com.robosoft.foursquare.adapter.ViewPagerAdapter
import com.robosoft.foursquare.databinding.ActivityHomeBinding
import com.robosoft.foursquare.view.activity.menuitems.AboutusActivity
import com.robosoft.foursquare.view.activity.menuitems.FavouriteActivity
import com.robosoft.foursquare.view.activity.menuitems.FeedbackActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var homeBinding: ActivityHomeBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        private const val LOCATION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        drawerLayout = homeBinding.drawerLayout
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        homeBinding.toolbar.menu.setOnClickListener {
            drawerLayout.open()
        }

        val navigationView: NavigationView = homeBinding.navigationView

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_favourites -> {
                    startActivity(Intent(this, FavouriteActivity::class.java))
                }
                R.id.nav_feedback -> {
                    startActivity(Intent(this, FeedbackActivity::class.java))
                }
                R.id.nav_about_us -> {
                    startActivity(Intent(this, AboutusActivity::class.java))
                }
                R.id.nav_logout -> {
                    Toast.makeText(applicationContext, "Logout", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

        tabLayout = homeBinding.tabLayout
        viewPager2 = homeBinding.viewPager2
        viewPager2.isUserInputEnabled = false

        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_REQUEST_CODE
            )
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->

            if (location != null) {
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                viewPager2.adapter = ViewPagerAdapter(this,currentLatLong)
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}