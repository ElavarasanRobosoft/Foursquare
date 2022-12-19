package com.robosoft.foursquare.view.fragment.home

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.robosoft.foursquare.R
import com.robosoft.foursquare.databinding.FragmentNearYouBinding


class NearYouFragment : Fragment(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private lateinit var nearYouBinding: FragmentNearYouBinding
    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        private const val LOCATION_REQUEST_CODE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        nearYouBinding = FragmentNearYouBinding.inflate(inflater,container,false)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity?.applicationContext!!)

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
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                context as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_REQUEST_CODE)
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->

            if (location != null){
                lastLocation = location
                val currentLatLong = LatLng(location.latitude,location.longitude)
                placeMarkerOnMap(currentLatLong)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong,12f))
            }

        }
        }

    private fun placeMarkerOnMap(currentLatLong: LatLng) {
        val markerOptions = MarkerOptions().position(currentLatLong)
        markerOptions.title("$currentLatLong")
        mMap.addMarker(markerOptions)
    }

    override fun onMarkerClick(p0: Marker?) = true

}