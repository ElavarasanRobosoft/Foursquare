package com.robosoft.foursquare.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.robosoft.foursquare.R
import com.robosoft.foursquare.databinding.ActivityIndividualHotelContainerBinding
import com.robosoft.foursquare.view.fragment.LoginFragment
import com.robosoft.foursquare.view.fragment.individualhotel.HotelDetailFragment

class IndividualHotelContainerActivity : AppCompatActivity() {

    private lateinit var individualHotelContainerBinding: ActivityIndividualHotelContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        individualHotelContainerBinding = ActivityIndividualHotelContainerBinding.inflate(layoutInflater)
        setContentView(individualHotelContainerBinding.root)

        val intent = intent
        val placeId = intent.getStringExtra("placeId")
        val placeName = intent.getStringExtra("placeName")
        val distance = intent.getStringExtra("distance")

        if (savedInstanceState == null){
            val bundle = Bundle()
            bundle.putString("placeId",placeId)
            bundle.putString("placeName",placeName)
            bundle.putString("distance",distance)
            val fragment = HotelDetailFragment()
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(
                individualHotelContainerBinding.hotelContainer.id,
                fragment
            ).commit()
        }
    }
}