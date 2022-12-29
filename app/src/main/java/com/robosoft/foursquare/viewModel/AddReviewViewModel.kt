package com.robosoft.foursquare.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robosoft.foursquare.model.dataclass.ResponseMessage
import com.robosoft.foursquare.model.dataclass.feedback.FeedbackBody
import com.robosoft.foursquare.model.dataclass.feedback.FeedbackResponse
import com.robosoft.foursquare.model.network.ProjectApi
import com.robosoft.foursquare.model.network.ServiceBuilderObject
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddReviewViewModel: ViewModel() {

    private val retrofit = ServiceBuilderObject.buildService(ProjectApi::class.java)

    private var ReviewLiveData: MutableLiveData<ResponseMessage?> = MutableLiveData()

    fun getReviewDataObserver(): MutableLiveData<ResponseMessage?> {
        return ReviewLiveData
    }

    fun addReviews(accessToken: String, placeId: RequestBody,review: RequestBody) {
        retrofit.addReviews(accessToken, placeId,review).enqueue(object : Callback<ResponseMessage> {
            override fun onFailure(call: Call<ResponseMessage>, t: Throwable) {
                ReviewLiveData.postValue(null)
                Log.d("add review fail response",t.toString())
            }
            override fun onResponse(
                call: Call<ResponseMessage>,
                response: Response<ResponseMessage>
            ) {
                ReviewLiveData.postValue(response.body())
                Log.d("add review success response", response.toString())
            }
        })
    }

}