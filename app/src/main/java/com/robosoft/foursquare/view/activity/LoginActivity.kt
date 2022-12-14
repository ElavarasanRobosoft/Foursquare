package com.robosoft.foursquare.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.robosoft.foursquare.R
import com.robosoft.foursquare.databinding.ActivityLoginBinding
import com.robosoft.foursquare.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewmodel = loginViewModel
        binding.lifecycleOwner = this

        loginViewModel.email.observe(this, Observer{
            Log.d("email", it.toString())
        })
    }
}