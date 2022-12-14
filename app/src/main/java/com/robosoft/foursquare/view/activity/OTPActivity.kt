package com.robosoft.foursquare.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.robosoft.foursquare.R
import com.robosoft.foursquare.databinding.ActivityOtpactivityBinding

class OTPActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpactivity)

        binding = ActivityOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}