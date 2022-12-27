package com.robosoft.foursquare.view.fragment.individualhotel

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.robosoft.foursquare.R
import com.robosoft.foursquare.SharedPreferenceManager
import com.robosoft.foursquare.databinding.FragmentHotelDetailBinding
import com.robosoft.foursquare.model.dataclass.favourites.AddFavouriteBody
import com.robosoft.foursquare.model.dataclass.individualhotel.getParticularPlaceDetailsBody
import com.robosoft.foursquare.model.dataclass.individualhotel.getParticularPlaceDetailsResponse
import com.robosoft.foursquare.model.network.ProjectService
import com.robosoft.foursquare.viewModel.HotelDetailViewModel
import com.squareup.picasso.Picasso


class HotelDetailFragment : Fragment(){

    private lateinit var hotelDetailBinding: FragmentHotelDetailBinding
    private lateinit var mMap: GoogleMap
    private lateinit var viewModel: HotelDetailViewModel
    private val projectApi = ProjectService()

    private lateinit var particularPlace: getParticularPlaceDetailsResponse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        hotelDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_hotel_detail, container, false)
        val bundle = arguments
        val placeId = bundle?.getString("placeId")
        val placeName = bundle?.getString("placeName")
        val distance = bundle?.getString("distance")
        val rating = bundle?.getString("rating")
        val favourite = bundle?.getString("favourite")

        var fav = false
        Log.d("favourite", favourite.toString())

        val placeBundle = Bundle()
        placeBundle.putString("placeId", placeId)
        placeBundle.putString("placeName", placeName)

        viewModel = ViewModelProvider(this)[HotelDetailViewModel::class.java]

        hotelDetailBinding.backIbn.setOnClickListener {
            activity?.finish()
        }

        hotelDetailBinding.favIbn.setOnClickListener {
            if (!fav){
                hotelDetailBinding.favIbn.setImageResource(R.drawable.favourite_whit)
                favourite(placeId!!)
                fav = true
            }
            else {
                hotelDetailBinding.favIbn.setImageResource(R.drawable.favourite_icon)
                favourite(placeId!!)
                fav = false
            }

        }

        hotelDetailBinding.shareIbn.setOnClickListener {
            Toast.makeText(activity?.applicationContext, "share", Toast.LENGTH_SHORT).show()
        }

        hotelDetailBinding.ratingIbn.setOnClickListener {
            val builder = AlertDialog.Builder(requireActivity())
                .create()

            val view = layoutInflater.inflate(R.layout.rating_alertbox, null)
            val closeButton = view.findViewById<ImageButton>(R.id.close_rating)
            val ratingText = view.findViewById<TextView>(R.id.overall_rating)
//            val ratingValue = view.findViewById<Ra>()
            val submitRating = view.findViewById<TextView>(R.id.submit_rating)
            builder.setView(view)

            ratingText.text = rating

            closeButton.setOnClickListener {
                builder.dismiss()
            }
            submitRating.setOnClickListener {
                Toast.makeText(activity?.applicationContext, "submit", Toast.LENGTH_SHORT).show()
            }
            builder.setCanceledOnTouchOutside(false)
            builder.window?.setBackgroundDrawableResource(R.color.transparent)
            builder.show()
        }

        hotelDetailBinding.photoIbn.setOnClickListener {
            val photo = PhotoFragment()
            photo.arguments = placeBundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.hotel_container, photo)?.addToBackStack(null)?.commit()
        }

        hotelDetailBinding.reviewIbn.setOnClickListener {
            val review = ReviewFragment()
            review.arguments = placeBundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.hotel_container, review)?.addToBackStack(null)?.commit()
        }

        val data = getParticularPlaceDetailsBody(placeId!!, placeName!!)

        viewModel.getHotelDetailDataObserver().observe(viewLifecycleOwner, Observer {
            if (it == null) {
                Toast.makeText(
                    activity?.applicationContext,
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Log.d("response", it.toString())

                particularPlace = it

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

                hotelDetailBinding.hotelName.text = it.placeName
                hotelDetailBinding.hotelDesc.text = it.keywords
                hotelDetailBinding.descTv.text = it.overview
                hotelDetailBinding.hotelAddressTv.text = it.address
                hotelDetailBinding.hotelContactTv.text = it.phoneNumber
                hotelDetailBinding.hotelDistanceTv.text = "Drive : $distance Km"

                val mapFragment = childFragmentManager
                    .findFragmentById(R.id.hotel_map_view) as SupportMapFragment
                mapFragment.getMapAsync{
                    mMap = it
                    val placeLatLong = LatLng(particularPlace.location.coordinates[1],particularPlace.location.coordinates[0])

                    mMap?.apply {
                        addMarker(MarkerOptions().position(placeLatLong).title(particularPlace.placeName))
                        moveCamera(CameraUpdateFactory.newLatLngZoom(placeLatLong,12f))
                    }
                }
            }

        })
        viewModel.getParticularPlaceDetails(data)


        hotelDetailBinding.addReview.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.hotel_container, AddReviewFragment())?.addToBackStack(null)?.commit()
        }
        return hotelDetailBinding.root
    }

    fun favourite(placeId: String){
        val sharedPreferences =
            activity?.applicationContext?.getSharedPreferences(
                "sharedPreference",
                Context.MODE_PRIVATE
            )
        val accessToken = activity?.applicationContext?.let { SharedPreferenceManager(it).getAccessToken() }
        val data = AddFavouriteBody(placeId)
        projectApi.addToFavourites(accessToken!!,data){
            if (it == null){
                Toast.makeText(activity?.applicationContext,"Something went wrong", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(activity?.applicationContext,it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}