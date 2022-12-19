package com.robosoft.foursquare.view.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robosoft.foursquare.R
import com.robosoft.foursquare.databinding.FragmentLoginBinding
import com.robosoft.foursquare.databinding.FragmentNearYouBinding


class NearYouFragment : Fragment() {

    private lateinit var nearYouBinding: FragmentNearYouBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        nearYouBinding = FragmentNearYouBinding.inflate(inflater,container,false)



        return nearYouBinding.root
    }

}