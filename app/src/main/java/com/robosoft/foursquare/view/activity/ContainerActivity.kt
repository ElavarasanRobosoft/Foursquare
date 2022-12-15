package com.robosoft.foursquare.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.robosoft.foursquare.R
import com.robosoft.foursquare.databinding.ActivityContainerBinding
import com.robosoft.foursquare.view.activity.fragment.LoginFragment

class ContainerActivity : AppCompatActivity() {

    private lateinit var containerActivityBinding: ActivityContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        containerActivityBinding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(containerActivityBinding.root)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(
                containerActivityBinding.fragmentContainer.id,
                LoginFragment()
            ).commit()
        }
    }
}