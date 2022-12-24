package com.robosoft.foursquare.view.fragment.individualhotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robosoft.foursquare.R
import com.robosoft.foursquare.databinding.FragmentIndividualPhotoBinding


class IndividualPhotoFragment : Fragment() {


    private lateinit var individualPhotoBinding: FragmentIndividualPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_individual_photo, container, false)
    }

}