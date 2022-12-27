package com.robosoft.foursquare.view.fragment.individualhotel

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.robosoft.foursquare.R
import com.robosoft.foursquare.SharedPreferenceManager
import com.robosoft.foursquare.databinding.FragmentIndividualPhotoBinding
import com.robosoft.foursquare.model.dataclass.favourites.AddFavouriteBody
import com.robosoft.foursquare.model.network.ProjectService
import com.robosoft.foursquare.viewModel.IndividualPhotoViewModel
import com.squareup.picasso.Picasso


class IndividualPhotoFragment : Fragment() {


    private lateinit var individualPhotoBinding: FragmentIndividualPhotoBinding
    private lateinit var viewModel: IndividualPhotoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        individualPhotoBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_individual_photo, container, false)


        val bundle = arguments
        val image = bundle?.getString("image")
        val profileImg = bundle?.getString("profileImg")
        val userName = bundle?.getString("userName")
        val date = bundle?.getString("date")


        individualPhotoBinding.backIbn.setOnClickListener {

        }


        val imageUrl = image
        individualPhotoBinding.reviewImg.let {
            val uri = Uri.parse(imageUrl)
            Picasso.with(activity).load(uri).into(it)
        }

        val profileImageUrl = profileImg
        individualPhotoBinding.profilePic.let {
            val uri = Uri.parse(profileImageUrl)
            Picasso.with(activity).load(uri).into(it)
        }

        individualPhotoBinding.username.text = userName

        individualPhotoBinding.date.text = date?.removeRange(9,(date).length)


        return individualPhotoBinding.root
    }


}