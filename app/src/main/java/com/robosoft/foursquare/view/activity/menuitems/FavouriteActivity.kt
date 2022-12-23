package com.robosoft.foursquare.view.activity.menuitems

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.robosoft.foursquare.R
import com.robosoft.foursquare.SharedPreferenceManager
import com.robosoft.foursquare.adapter.ViewModelRecyclerAdapter
import com.robosoft.foursquare.databinding.ActivityFavouriteBinding
import com.robosoft.foursquare.model.dataclass.hotel.HotelBody
import com.robosoft.foursquare.viewModel.CoffeeViewModel
import com.robosoft.foursquare.viewModel.FavouriteViewModel
import com.robosoft.foursquare.viewModel.FeedbackViewModel

class FavouriteActivity : AppCompatActivity() {

    private lateinit var favouriteBinding: ActivityFavouriteBinding
    private lateinit var viewModel: FavouriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favouriteBinding = DataBindingUtil.setContentView(this,R.layout.activity_favourite)

        val sharedPreferences =
            this.getSharedPreferences(
                "sharedPreference",
                Context.MODE_PRIVATE
            )
        val accessToken = SharedPreferenceManager(this).getAccessToken()
        val currentLat = sharedPreferences.getString("currentLat", "")!!
        val currentLong = sharedPreferences.getString("currentLong", "")!!

        Log.d("lat",currentLat)

        viewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]

        favouriteBinding.backIbn.setOnClickListener {
            onBackPressed()
        }

        favouriteBinding.filterIbn.setOnClickListener {
            Toast.makeText(this,"Filter",Toast.LENGTH_SHORT).show()
        }

        val data = HotelBody(currentLat,currentLong)
        viewModel.getFavouriteLiveDataObserver().observe(this, Observer {
            if (it != null) {
                Log.d("data",data.toString())
                Log.d("favourite response", it.toString())
                Log.d("accessToken",accessToken)
                favouriteBinding.favouritesRecyclerView.layoutManager =
                    LinearLayoutManager(this)
                favouriteBinding.favouritesRecyclerView.adapter =
                    ViewModelRecyclerAdapter(this, it,lifecycleScope)
            } else {
                Toast.makeText(
                    this,
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        viewModel.getFavourite(accessToken, data)
    }
}