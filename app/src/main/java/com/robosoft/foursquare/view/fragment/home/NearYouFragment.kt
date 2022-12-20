package com.robosoft.foursquare.view.fragment.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.robosoft.foursquare.R
import com.robosoft.foursquare.adapter.RecyclerAdapter
import com.robosoft.foursquare.databinding.FragmentNearYouBinding
import com.robosoft.foursquare.model.dataclass.hotel.HotelBody
import com.robosoft.foursquare.model.network.ProjectService


class NearYouFragment() : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var nearYouBinding: FragmentNearYouBinding
    private lateinit var mMap: GoogleMap
    private lateinit var currentLatLong: LatLng
    private val projectApi = ProjectService()
//    private lateinit var lastLocation: Location
//    private lateinit var fusedLocationClient: FusedLocationProviderClient

//    companion object {
//        private const val LOCATION_REQUEST_CODE = 1
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        nearYouBinding = FragmentNearYouBinding.inflate(inflater, container, false)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync(this)


        val latitude = arguments?.getString("lat")
        val longitude = arguments?.getString("long")
        if (latitude != null) {
            if (longitude != null) {
                currentLatLong = LatLng(latitude.toDouble(), longitude.toDouble())
            }
        }
        val data =
            HotelBody(currentLatLong.latitude.toString(), currentLatLong.longitude.toString())
        Log.d("currentLatLong", data.toString())
        projectApi.getNearByPlaces(data) {
            if (it != null) {
                Log.d("response", it.toString())
                nearYouBinding.nearYouRecyclerView.adapter =
                    RecyclerAdapter(activity?.applicationContext, it)
                nearYouBinding.nearYouRecyclerView.layoutManager =
                    LinearLayoutManager(activity?.applicationContext)

            } else {
                Toast.makeText(
                    activity?.applicationContext,
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


//        fusedLocationClient =
//            LocationServices.getFusedLocationProviderClient(activity?.applicationContext!!)

        return nearYouBinding.root
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {
            mMap = googleMap

            mMap.uiSettings.isZoomControlsEnabled = true
            mMap.setOnMarkerClickListener(this)
            setUpMap()
        }
    }

    private fun setUpMap() {

        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mMap.isMyLocationEnabled = true
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 12f))

//        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
//            if (location != null) {
//                lastLocation = location
////                val currentLatLong = LatLng(location.latitude, location.longitude)
//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 12f))
//            }
//        }
    }

    override fun onMarkerClick(p0: Marker?) = true
}