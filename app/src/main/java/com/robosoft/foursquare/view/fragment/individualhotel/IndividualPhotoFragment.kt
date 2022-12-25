package com.robosoft.foursquare.view.fragment.individualhotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.robosoft.foursquare.R
import com.robosoft.foursquare.databinding.FragmentIndividualPhotoBinding
import com.robosoft.foursquare.viewModel.IndividualPhotoViewModel


class IndividualPhotoFragment : Fragment() {


    private lateinit var individualPhotoBinding: FragmentIndividualPhotoBinding
    private lateinit var viewModel: IndividualPhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        individualPhotoBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_individual_photo, container, false)

        individualPhotoBinding.backIbn.setOnClickListener {

        }
        return individualPhotoBinding.root
    }

}