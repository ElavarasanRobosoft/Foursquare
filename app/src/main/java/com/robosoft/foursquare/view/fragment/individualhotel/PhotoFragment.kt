package com.robosoft.foursquare.view.fragment.individualhotel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.robosoft.foursquare.R
import com.robosoft.foursquare.adapter.PhotoAdapter
import com.robosoft.foursquare.databinding.FragmentPhotoBinding
import com.robosoft.foursquare.model.dataclass.review.GetReviewResponseBody
import com.robosoft.foursquare.viewModel.PhotoViewModel


class  PhotoFragment : Fragment() {

    private lateinit var photoBinding: FragmentPhotoBinding
    private lateinit var viewModel: PhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        photoBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_photo, container, false)
        val placeBundle = arguments
        val placeId = placeBundle?.getString("placeId")
        val placeName = placeBundle?.getString("placeName")

        photoBinding.photoBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        photoBinding.hotelName.text = placeName
        val data = GetReviewResponseBody(placeId.toString())

        photoBinding.addPhotoIbn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.hotel_container,AddReviewFragment())?.commit()
        }

        viewModel = ViewModelProvider(this)[PhotoViewModel::class.java]
        viewModel.getPhotoLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if (it?.message == "images found") {
                Log.d("review response", it.toString())
                photoBinding.photoRecyclerView.layoutManager =
                    GridLayoutManager(activity?.applicationContext,3)
                photoBinding.photoRecyclerView.adapter =
                    PhotoAdapter(activity,it,lifecycleScope)
            } else {
                Log.d("place id", placeId.toString())
                Toast.makeText(
                    activity?.applicationContext,
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        viewModel.getImagesByPlaceId(data)


        return photoBinding.root
    }
}