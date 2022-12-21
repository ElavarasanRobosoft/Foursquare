package com.robosoft.foursquare.view.fragment.individualhotel

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.robosoft.foursquare.R
import com.robosoft.foursquare.databinding.FragmentHotelDetailBinding
import com.robosoft.foursquare.model.dataclass.individualhotel.getParticularPlaceDetailsBody
import com.robosoft.foursquare.model.network.ProjectService
import com.squareup.picasso.Picasso


class HotelDetailFragment : Fragment() {

    private lateinit var hotelDetailBinding: FragmentHotelDetailBinding
    private lateinit var mMap: GoogleMap
    private val projectApi = ProjectService()
    private lateinit var latLng: LatLng

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        hotelDetailBinding = FragmentHotelDetailBinding.inflate(inflater, container, false)


        val bundle = arguments
        val placeId = bundle?.getString("placeId")
        val placeName = bundle?.getString("placeName")

        hotelDetailBinding.backIbn.setOnClickListener {
            activity?.finish()
        }

        hotelDetailBinding.shareIbn.setOnClickListener {
            Toast.makeText(activity?.applicationContext, "share", Toast.LENGTH_SHORT).show()
        }

        hotelDetailBinding.ratingIbn.setOnClickListener {
            Toast.makeText(activity?.applicationContext, "Rating", Toast.LENGTH_SHORT).show()
        }

        hotelDetailBinding.photoIbn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.hotel_container, PhotoFragment())?.addToBackStack(null)?.commit()
        }

        hotelDetailBinding.reviewIbn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.hotel_container, PhotoFragment())?.addToBackStack(null)?.commit()
        }
        hotelDetailBinding.favIbn.setOnClickListener {
            Toast.makeText(activity?.applicationContext, "Favourite", Toast.LENGTH_SHORT).show()
        }


        val data = getParticularPlaceDetailsBody(placeId!!, placeName!!)
        projectApi.getParticularPlaceDetails(data) {
            if (it == null) {
                Toast.makeText(
                    activity?.applicationContext,
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Log.d("response", it.toString())
                val imageUrl = it.placeImages.url
                hotelDetailBinding.backgroundImg.let { it ->
                    val uri = Uri.parse(imageUrl)
                    Picasso.with(activity).load(uri).into(it)
                }

                when (it.totalrating.toInt() / 2) {
                    1 -> {
                        hotelDetailBinding.startOne.setImageResource(R.drawable.rating_icon_selected_2x)
                        hotelDetailBinding.startTwo.setImageResource(R.drawable.rating_icon_unselected_2x)
                        hotelDetailBinding.startThree.setImageResource(R.drawable.rating_icon_unselected_2x)
                        hotelDetailBinding.startFour.setImageResource(R.drawable.rating_icon_unselected_2x)
                        hotelDetailBinding.startFive.setImageResource(R.drawable.rating_icon_unselected_2x)
                    }
                    2 -> {
                        hotelDetailBinding.startOne.setImageResource(R.drawable.rating_icon_selected_2x)
                        hotelDetailBinding.startTwo.setImageResource(R.drawable.rating_icon_selected_2x)
                        hotelDetailBinding.startThree.setImageResource(R.drawable.rating_icon_unselected_2x)
                        hotelDetailBinding.startFour.setImageResource(R.drawable.rating_icon_unselected_2x)
                        hotelDetailBinding.startFive.setImageResource(R.drawable.rating_icon_unselected_2x)

                    }
                    3 -> {
                        hotelDetailBinding.startOne.setImageResource(R.drawable.rating_icon_selected_2x)
                        hotelDetailBinding.startTwo.setImageResource(R.drawable.rating_icon_selected_2x)
                        hotelDetailBinding.startThree.setImageResource(R.drawable.rating_icon_selected_2x)
                        hotelDetailBinding.startFour.setImageResource(R.drawable.rating_icon_unselected_2x)
                        hotelDetailBinding.startFive.setImageResource(R.drawable.rating_icon_unselected_2x)

                    }
                    4 -> {
                        hotelDetailBinding.startOne.setImageResource(R.drawable.rating_icon_selected_2x)
                        hotelDetailBinding.startTwo.setImageResource(R.drawable.rating_icon_selected_2x)
                        hotelDetailBinding.startThree.setImageResource(R.drawable.rating_icon_selected_2x)
                        hotelDetailBinding.startFour.setImageResource(R.drawable.rating_icon_selected_2x)
                        hotelDetailBinding.startFive.setImageResource(R.drawable.rating_icon_unselected_2x)

                    }
                    5 -> {
                        hotelDetailBinding.startOne.setImageResource(R.drawable.rating_icon_selected_2x)
                        hotelDetailBinding.startTwo.setImageResource(R.drawable.rating_icon_selected_2x)
                        hotelDetailBinding.startThree.setImageResource(R.drawable.rating_icon_selected_2x)
                        hotelDetailBinding.startFour.setImageResource(R.drawable.rating_icon_selected_2x)
                        hotelDetailBinding.startFive.setImageResource(R.drawable.rating_icon_selected_2x)
                    }
                    else -> {
                        hotelDetailBinding.startOne.setImageResource(R.drawable.rating_icon_unselected_2x)
                        hotelDetailBinding.startTwo.setImageResource(R.drawable.rating_icon_unselected_2x)
                        hotelDetailBinding.startThree.setImageResource(R.drawable.rating_icon_unselected_2x)
                        hotelDetailBinding.startFour.setImageResource(R.drawable.rating_icon_unselected_2x)
                        hotelDetailBinding.startFive.setImageResource(R.drawable.rating_icon_unselected_2x)
                    }
                }

                latLng = LatLng(it.location.coordinates[0], it.location.coordinates[1])
                hotelDetailBinding.hotelName.text = it.placeName
                hotelDetailBinding.descTv.text = it.overview
                hotelDetailBinding.hotelAddressTv.text = it.address
                hotelDetailBinding.hotelContactTv.text = it.phoneNumber
            }
        }
        hotelDetailBinding.addReview.setOnClickListener {
            Toast.makeText(activity?.applicationContext, "Add Review", Toast.LENGTH_SHORT).show()
        }
        return hotelDetailBinding.root
    }
}