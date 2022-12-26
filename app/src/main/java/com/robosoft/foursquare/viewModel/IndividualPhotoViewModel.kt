package com.robosoft.foursquare.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//import com.robosoft.foursquare.model.dataclass.photoReview.ParticularImageResponse
//import com.robosoft.foursquare.model.dataclass.photoReview.ParticularPhotoBody
import com.robosoft.foursquare.model.network.ProjectApi
import com.robosoft.foursquare.model.network.ServiceBuilderObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IndividualPhotoViewModel: ViewModel() {
//    private val retrofit = ServiceBuilderObject.buildService(ProjectApi::class.java)
//
//    private var IndividualPhotoeDataList: MutableLiveData<ParticularImageResponse?> = MutableLiveData()
//
//    fun getIndividualPhotoLiveDataObserver(): MutableLiveData<ParticularImageResponse?> {
//        return IndividualPhotoeDataList
//    }
//
//    fun getDetailsOfParticularImage(data: ParticularPhotoBody) {
//        retrofit.getDetailsOfParticularImage(data).enqueue(object : Callback<ParticularImageResponse> {
//            override fun onFailure(call: Call<ParticularImageResponse>, t: Throwable) {
//                IndividualPhotoeDataList.postValue(null)
//                Log.d("topPick response",t.toString())
//            }
//            override fun onResponse(
//                call: Call<ParticularImageResponse>,
//                response: Response<ParticularImageResponse>
//            ) {
//                IndividualPhotoeDataList.postValue(response.body())
//                Log.d("topPick response", response.toString())
//            }
//        })
//    }
}