package com.robosoft.foursquare.view.activity.menuitems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.robosoft.foursquare.databinding.ActivityAboutusBinding
import com.robosoft.foursquare.model.network.ProjectApi

class AboutusActivity : AppCompatActivity() {

    private lateinit var aboutusBinding: ActivityAboutusBinding
    private lateinit var projectApi: ProjectApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aboutusBinding = ActivityAboutusBinding.inflate(layoutInflater)
        setContentView(aboutusBinding.root)

        aboutusBinding.backAboutIbn.setOnClickListener {
            onBackPressed()
        }
//        val message = projectApi.aboutUS()
//        if (message.isSuccessful){
//            aboutusBinding.aboutUs.text = message.message()
//        }
    }
}