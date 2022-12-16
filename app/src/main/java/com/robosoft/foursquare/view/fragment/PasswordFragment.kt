package com.robosoft.foursquare.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robosoft.foursquare.R
import com.robosoft.foursquare.databinding.FragmentPasswordBinding
import com.robosoft.foursquare.model.network.ProjectService

class PasswordFragment : Fragment() {

    private lateinit var passwordBinding: FragmentPasswordBinding
    private val projectApi = ProjectService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        passwordBinding = FragmentPasswordBinding.inflate(inflater,container,false)
        return passwordBinding.root
    }
}