package com.robosoft.foursquare.view.activity.menuitems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.robosoft.foursquare.databinding.ActivityAboutusBinding
import com.robosoft.foursquare.model.network.ProjectApi
import com.robosoft.foursquare.model.network.ProjectService

class AboutusActivity : AppCompatActivity() {

    private lateinit var aboutusBinding: ActivityAboutusBinding
    private lateinit var projectApi: ProjectService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aboutusBinding = ActivityAboutusBinding.inflate(layoutInflater)
        setContentView(aboutusBinding.root)

        aboutusBinding.backAboutIbn.setOnClickListener {
            onBackPressed()
        }
        projectApi.aboutUS {
            if (it != null){
                Log.d("response",it.message)
                aboutusBinding.aboutUs.text = it.message
            }else{
                Toast.makeText(applicationContext,"Something went wrong",Toast.LENGTH_SHORT).show()
            }
        }
    }
}