package com.robosoft.foursquare.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.robosoft.foursquare.R
import com.robosoft.foursquare.adapter.NearByCityAdapter
import com.robosoft.foursquare.adapter.SearchPlaceAdapter
import com.robosoft.foursquare.databinding.ActivitySearchBinding
import com.robosoft.foursquare.model.dataclass.hotel.HotelBody
import com.robosoft.foursquare.model.dataclass.search.SearchPlaceBody
import com.robosoft.foursquare.viewModel.SearchViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var searchBinding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchBinding = DataBindingUtil.setContentView(this,R.layout.activity_search)

        searchBinding.backIbn.setOnClickListener {
            onBackPressed()
        }

        searchBinding.filterIbn.setOnClickListener {
            startActivity(Intent(this,FilterActivity::class.java))
        }

        val sharedPreferences =
            this.getSharedPreferences(
                "sharedPreference",
                Context.MODE_PRIVATE
            )
        val currentLat = sharedPreferences.getString("currentLat", "")!!
        val currentLong = sharedPreferences.getString("currentLong", "")!!

        val data = HotelBody(currentLat,currentLong)

        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        nearByCity()
        searchFromFavourite(data)

        searchBinding.search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                getPlace()
                val searchData = SearchPlaceBody(currentLat,currentLong,query!!)
                getPlaceData(searchData)
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                getPlace()
                val searchData = SearchPlaceBody(currentLat,currentLong,text!!)
                getPlaceData(searchData)
                return false
            }
        })

//        searchBinding.mapSubmitView.setOnClickListener {
//            Toast.makeText(this,"map",Toast.LENGTH_SHORT).show()
//            searchBinding.search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    getPlaceMap()
//                    val searchData = SearchPlaceBody(currentLat,currentLong,query!!)
//                    getPlaceData(searchData)
//                    return false
//                }
//
//                override fun onQueryTextChange(text: String?): Boolean {
//                    getPlaceMap()
//                    val searchData = SearchPlaceBody(currentLat,currentLong,text!!)
//                    getPlaceData(searchData)
//                    return false
//                }
//            })
//        }
    }


    fun nearByCity(){
        viewModel.getFavouriteLiveDataObserver().observe(this, Observer {
            if (it != null) {
                searchBinding.searchPlace.visibility = View.INVISIBLE
                searchBinding.mapRecyclerView.visibility = View.INVISIBLE
                searchBinding.searchHome.visibility = View.VISIBLE
                searchBinding.searchRecyclerView.layoutManager =
                    LinearLayoutManager(this)
                searchBinding.searchRecyclerView.adapter =
                    NearByCityAdapter(this, it,lifecycleScope,searchBinding.search)
            } else {
                Toast.makeText(
                    this,
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun searchFromFavourite(data: HotelBody) {
        viewModel.getNearByCity(data)
    }

    fun getPlace(){
        viewModel.getSearchPlaceLiveDataObserver().observe(this, Observer {
            if (it != null) {
                searchBinding.searchHome.visibility = View.INVISIBLE
                searchBinding.mapRecyclerView.visibility = View.INVISIBLE
                searchBinding.searchPlace.visibility = View.VISIBLE
                searchBinding.placeRecyclerView.layoutManager =
                    LinearLayoutManager(this)
                searchBinding.placeRecyclerView.adapter =
                    SearchPlaceAdapter(this, it,lifecycleScope)
            } else {
                Toast.makeText(
                    this,
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

//    fun getPlaceMap(){
//        viewModel.getSearchPlaceLiveDataObserver().observe(this, Observer {
//            if (it != null) {
//                searchBinding.searchHome.visibility = View.INVISIBLE
//                searchBinding.searchPlace.visibility = View.INVISIBLE
//                searchBinding.mapRecyclerView.visibility = View.VISIBLE
//                searchBinding.placeRecyclerView.layoutManager =
//                    LinearLayoutManager(this,RecyclerView.HORIZONTAL, false)
//                searchBinding.placeRecyclerView.adapter =
//                    SearchPlaceAdapter(this, it,lifecycleScope)
//            } else {
//                Toast.makeText(
//                    this,
//                    "Something went wrong",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        })
//    }

    fun getPlaceData(data: SearchPlaceBody) {
        viewModel.getSearchPlace(data)
    }
}