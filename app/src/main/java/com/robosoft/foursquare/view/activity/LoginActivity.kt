package com.robosoft.foursquare.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.robosoft.foursquare.R
import com.robosoft.foursquare.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}