package com.robosoft.foursquare.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robosoft.foursquare.R
import com.robosoft.foursquare.databinding.FragmentOtpBinding
import com.robosoft.foursquare.model.network.ProjectService

class OtpFragment : Fragment() {

    private lateinit var otpBinding: FragmentOtpBinding
    private val projectApi = ProjectService()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        otpBinding = FragmentOtpBinding.inflate(inflater,container,false)
        return otpBinding.root
    }
}