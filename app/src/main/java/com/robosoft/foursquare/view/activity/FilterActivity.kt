package com.robosoft.foursquare.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.robosoft.foursquare.R
import com.robosoft.foursquare.adapter.FilterAdapter
import com.robosoft.foursquare.adapter.NearByCityAdapter
import com.robosoft.foursquare.adapter.ViewModelRecyclerAdapter
import com.robosoft.foursquare.databinding.ActivityFilterBinding
import com.robosoft.foursquare.model.dataclass.hotel.HotelBody
import com.robosoft.foursquare.viewModel.FilterViewModel
import com.robosoft.foursquare.viewModel.SearchViewModel

class FilterActivity : AppCompatActivity() {

    private lateinit var filterBinding: ActivityFilterBinding
    private lateinit var viewModel: FilterViewModel

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        val sharedPreferences =
            this.getSharedPreferences(
                "sharedPreference",
                Context.MODE_PRIVATE
            )
        val currentLat = sharedPreferences.getString("currentLat", "")!!
        val currentLong = sharedPreferences.getString("currentLong", "")!!

        filterBinding = DataBindingUtil.setContentView(this, R.layout.activity_filter)

        viewModel = ViewModelProvider(this)[FilterViewModel::class.java]

        val data = mutableMapOf<String, Any>()

        filterBinding.backIbn.setOnClickListener {
            onBackPressed()
        }

        var popular = false
        var distance = false
        var rating = false


        filterBinding.popular.setOnClickListener {
            if (!popular) {
                filterBinding.popular.setImageResource(R.drawable.popular_selected)
                popular = true
                filterBinding.distance.setImageResource(R.drawable.distance)
                distance = false
                filterBinding.rating.setImageResource(R.drawable.rating)
                rating = false
            } else {
                filterBinding.popular.setImageResource(R.drawable.popular)
                popular = false
            }
        }

        filterBinding.distance.setOnClickListener {
            if (!distance) {
                filterBinding.distance.setImageResource(R.drawable.distance_selected)
                distance = true
                filterBinding.popular.setImageResource(R.drawable.popular)
                popular = false
                filterBinding.rating.setImageResource(R.drawable.rating)
                rating = false
            } else {
                filterBinding.distance.setImageResource(R.drawable.distance)
                distance = false
            }
        }

        filterBinding.rating.setOnClickListener {
            if (!rating) {
                filterBinding.rating.setImageResource(R.drawable.rating_selected)
                rating = true
                filterBinding.distance.setImageResource(R.drawable.distance)
                distance = false
                filterBinding.popular.setImageResource(R.drawable.popular)
                popular = false
            } else {
                filterBinding.rating.setImageResource(R.drawable.rating)
                rating = false
            }
        }

        var rupeeOne = false
        var rupeeTwo = false
        var rupeeThree = false
        var rupeeFour = false

        filterBinding.rupee1.setOnClickListener {
            if (!rupeeOne) {
                filterBinding.rupee1.setImageResource(R.drawable.ruppe_btn1_selected)
                filterBinding.rupee2.setImageResource(R.drawable.ruppe_btn2)
                filterBinding.rupee3.setImageResource(R.drawable.ruppe_btn3)
                filterBinding.rupee4.setImageResource(R.drawable.ruppe_btn4)
                rupeeOne = true
                rupeeTwo = false
                rupeeThree = false
                rupeeFour = false
            } else {
                filterBinding.rupee1.setImageResource(R.drawable.ruppe_btn1)
                rupeeOne = false
            }
        }

        filterBinding.rupee2.setOnClickListener {
            if (!rupeeTwo) {
                filterBinding.rupee2.setImageResource(R.drawable.ruppe_btn2_selected)
                filterBinding.rupee1.setImageResource(R.drawable.ruppe_btn1)
                filterBinding.rupee3.setImageResource(R.drawable.ruppe_btn3)
                filterBinding.rupee4.setImageResource(R.drawable.ruppe_btn4)
                rupeeTwo = true
                rupeeOne = false
                rupeeThree = false
                rupeeFour = false

            } else {
                filterBinding.rupee2.setImageResource(R.drawable.ruppe_btn2)
                rupeeTwo = false
            }
        }

        filterBinding.rupee3.setOnClickListener {
            if (!rupeeThree) {
                filterBinding.rupee3.setImageResource(R.drawable.ruppe_btn3_selected)
                filterBinding.rupee1.setImageResource(R.drawable.ruppe_btn1)
                filterBinding.rupee2.setImageResource(R.drawable.ruppe_btn2)
                filterBinding.rupee4.setImageResource(R.drawable.ruppe_btn4)
                rupeeThree = true
                rupeeOne = false
                rupeeTwo = false
                rupeeFour = false
            } else {
                filterBinding.rupee3.setImageResource(R.drawable.ruppe_btn3)
                rupeeThree = false
            }
        }

        filterBinding.rupee4.setOnClickListener {
            if (!rupeeFour) {
                filterBinding.rupee4.setImageResource(R.drawable.ruppe_btn4_selected)
                filterBinding.rupee1.setImageResource(R.drawable.ruppe_btn1)
                filterBinding.rupee2.setImageResource(R.drawable.ruppe_btn2)
                filterBinding.rupee3.setImageResource(R.drawable.ruppe_btn3)
                rupeeFour = true
                rupeeOne = false
                rupeeTwo = false
                rupeeThree = false

            } else {
                filterBinding.rupee4.setImageResource(R.drawable.ruppe_btn4)
                rupeeFour = false
            }
        }

        var acceptCard = false
        var delivery = false
        var dogFriendly = false
        var familyFriendlyPlace = false
        var inWalkingDistance = false
        var outdoorSeating = false
        var parking = false
        var wifi = false


        filterBinding.acceptCard.setOnClickListener {
            if (!acceptCard) {
                filterBinding.acceptCardTv.setTextColor(R.color.selectedColor)
                filterBinding.acceptCardIv.setImageResource(R.drawable.filter_selected)
                acceptCard = true
                data["acceptcreditCards"] = true
            } else {
                filterBinding.acceptCardTv.setTextColor(R.color.unselectedColor)
                filterBinding.acceptCardIv.setImageResource(R.drawable.icon_add)
                acceptCard = false
                data.remove("acceptcreditCards")
            }
        }

        filterBinding.delivery.setOnClickListener {
            if (!delivery) {
                filterBinding.deliveryTv.setTextColor(R.color.selectedColor)
                filterBinding.deliveryIv.setImageResource(R.drawable.filter_selected)
                delivery = true
                data["delivery"] = true
            } else {
                filterBinding.deliveryTv.setTextColor(R.color.unselectedColor)
                filterBinding.deliveryIv.setImageResource(R.drawable.icon_add)
                delivery = false
                data.remove("delivery")
            }
        }

        filterBinding.dogFriendly.setOnClickListener {
            if (!dogFriendly) {
                filterBinding.dogFriendlyTv.setTextColor(R.color.selectedColor)
                filterBinding.dogFriendlyIv.setImageResource(R.drawable.filter_selected)
                dogFriendly = true
                data["dogFriendly"] = true
            } else {
                filterBinding.dogFriendlyTv.setTextColor(R.color.unselectedColor)
                filterBinding.dogFriendlyIv.setImageResource(R.drawable.icon_add)
                dogFriendly = false
                data.remove("dogFriendly")
            }
        }

        filterBinding.familyFriendlyPlaces.setOnClickListener {
            if (!familyFriendlyPlace) {
                filterBinding.familyFriendlyPlacesTv.setTextColor(R.color.selectedColor)
                filterBinding.familyFriendlyPlacesIv.setImageResource(R.drawable.filter_selected)
                familyFriendlyPlace = true
                data["familyFriendlyPlace"] = true
            } else {
                filterBinding.familyFriendlyPlacesTv.setTextColor(R.color.unselectedColor)
                filterBinding.familyFriendlyPlacesIv.setImageResource(R.drawable.icon_add)
                familyFriendlyPlace = false
                data.remove("familyFriendlyPlace")
            }
        }

        filterBinding.inWalkDistance.setOnClickListener {
            if (!inWalkingDistance) {
                filterBinding.inWalkDistanceTv.setTextColor(R.color.selectedColor)
                filterBinding.inWalkDistanceIv.setImageResource(R.drawable.filter_selected)
                inWalkingDistance = true
                data["inWalkingdistance"] = true
            } else {
                filterBinding.inWalkDistanceTv.setTextColor(R.color.unselectedColor)
                filterBinding.inWalkDistanceIv.setImageResource(R.drawable.icon_add)
                inWalkingDistance = false
                data.remove("inWalkingdistance")
            }
        }

        filterBinding.outdoorSeating.setOnClickListener {
            if (!outdoorSeating) {
                filterBinding.outdoorSeatingTv.setTextColor(R.color.selectedColor)
                filterBinding.outdoorSeatingIv.setImageResource(R.drawable.filter_selected)
                outdoorSeating = true
                data["outdoorSeating"] = true
            } else {
                filterBinding.outdoorSeatingTv.setTextColor(R.color.unselectedColor)
                filterBinding.outdoorSeatingIv.setImageResource(R.drawable.icon_add)
                outdoorSeating = false
                data.remove("outdoorSeating")
            }
        }

        filterBinding.parking.setOnClickListener {
            if (!parking) {
                filterBinding.parkingTv.setTextColor(R.color.selectedColor)
                filterBinding.parkingIv.setImageResource(R.drawable.filter_selected)
                parking = true
                data["parking"] = true
            } else {
                filterBinding.parkingTv.setTextColor(R.color.unselectedColor)
                filterBinding.parkingIv.setImageResource(R.drawable.icon_add)
                parking = false
                data.remove("parking")
            }
        }

        filterBinding.wifi.setOnClickListener {
            if (!wifi) {
                filterBinding.wifiTv.setTextColor(R.color.selectedColor)
                filterBinding.wifiIv.setImageResource(R.drawable.filter_selected)
                wifi = true
                data["wifi"] = true
            } else {
                filterBinding.wifiTv.setTextColor(R.color.unselectedColor)
                filterBinding.wifiIv.setImageResource(R.drawable.icon_add)
                wifi = false
                data.remove("wifi")
            }
        }


        filterBinding.doneTv.setOnClickListener {
            data["latitude"] = currentLat
            data["longitude"] = currentLong

            data["sortBy"]=
                if (popular) "popular"
                else if (distance) "totaldistance"
                else if (rating) "totalrating"
                else ""

            data["priceRange"] =
                if (rupeeOne) 9
                else if (rupeeTwo) 99
                else if (rupeeThree) 999
                else if (rupeeFour) 9999
                else 0

            Log.d("sort by",popular.toString())
            Log.d("sort by",distance.toString())
            Log.d("sort by",rating.toString())
            data["radius"] = filterBinding.radiusEt.text
            data["text"] = filterBinding.search.query.toString()
            Log.d("data",data.toString())
            getFilter()
            searchByFilter(data)
        }
    }

    private fun getFilter() {
        viewModel.getFilterLiveDataObserver().observe(this, Observer {
            if (it != null) {
                Log.d("response",it.toString())
                filterBinding.filterView.visibility = View.GONE
                filterBinding.filterRecyclerView.visibility = View.VISIBLE
                filterBinding.filterRecyclerView.layoutManager =
                    LinearLayoutManager(this)
                filterBinding.filterRecyclerView.adapter =
                    FilterAdapter(this, it, lifecycleScope)
            } else {
                Toast.makeText(
                    this,
                    "select values",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun searchByFilter(data: MutableMap<String, Any>) {
        viewModel.searchByFilter(data)
    }
}