package com.robosoft.foursquare.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robosoft.foursquare.R
import com.robosoft.foursquare.databinding.FragmentRegistrationBinding
import com.robosoft.foursquare.model.network.ProjectService


class RegistrationFragment : Fragment() {

    private lateinit var registrationBinding: FragmentRegistrationBinding
    private val projectApi = ProjectService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        registrationBinding = FragmentRegistrationBinding.inflate(inflater,container,false)

        val email = registrationBinding.emailEt.text
        val password = registrationBinding.enterPasswordEt.text
        val confirmPassword = registrationBinding.confirmPasswordEt.text
        val phone = registrationBinding.mobileNumberEt.text





        return registrationBinding.root
    }
}