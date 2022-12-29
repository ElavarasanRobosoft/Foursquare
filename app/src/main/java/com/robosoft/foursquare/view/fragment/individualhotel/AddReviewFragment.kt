package com.robosoft.foursquare.view.fragment.individualhotel

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.robosoft.foursquare.R
import com.robosoft.foursquare.SharedPreferenceManager
import com.robosoft.foursquare.adapter.NearByCityAdapter
import com.robosoft.foursquare.databinding.FragmentAddReviewBinding
import com.robosoft.foursquare.model.dataclass.hotel.HotelBody
import com.robosoft.foursquare.model.network.ProjectApi
import com.robosoft.foursquare.viewModel.AddReviewViewModel
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AddReviewFragment : Fragment() {

    private lateinit var addReviewBinding: FragmentAddReviewBinding
    private lateinit var viewModel: AddReviewViewModel
    val permissionList =
        arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        addReviewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_review, container, false)
        viewModel = ViewModelProvider(this)[AddReviewViewModel::class.java]

        val placeBundle = arguments
        val placeId = placeBundle?.getString("placeId")
        val placeName = placeBundle?.getString("placeName")

        addReviewBinding.reviewBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        addReviewBinding.hotelName.text = placeName


        addReviewBinding.reviewSubmit?.setOnClickListener {

            val sharedPreferences =
                activity?.applicationContext?.getSharedPreferences(
                    "sharedPreference",
                    Context.MODE_PRIVATE
                )
            val accessToken = SharedPreferenceManager(activity?.applicationContext!!).getAccessToken()
            val review = addReviewBinding.reviewEt?.text

            val place = RequestBody.create(MediaType.parse("multipart/form-data"),
                placeId.toString()
            )
            val userReview = RequestBody.create(MediaType.parse("multipart/form-data"),review.toString())

//            val requestbody: RequestBody =
//                MultipartBody.Builder()
//                    .setType(okhttp3.MultipartBody.FORM)
//                    .addFormDataPart("placeId", placeId.toString())
//                    .addFormDataPart("review", review.toString())
//                    .build()
////            addReviewPage()
//            Log.d("placeId",placeId.toString())
//            Log.d("review",review.toString())
////            addReviewBody(accessToken,requestbody)
//            Log.d("review",requestbody.toString())

            addReviewPage()
            addReviewBody(accessToken,place,userReview)
        }

        return addReviewBinding.root
    }

    fun addReviewPage() {
        viewModel.getReviewDataObserver().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Toast.makeText(
                    activity?.applicationContext,
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    activity?.applicationContext,
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun addReviewBody(accessToken: String,placeId: RequestBody,review : RequestBody) {
        viewModel.addReviews(accessToken,placeId, review)
    }

//    fun addReview(placeId: String, review: String){
//        val place = RequestBody.create(MediaType.parse("multipart/form-data"),placeId)
//        val userreview = RequestBody.create(MediaType.parse("multipart/form-data"),review)
//    }

}