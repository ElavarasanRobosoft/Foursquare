package com.robosoft.foursquare.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robosoft.foursquare.model.dataclass.hotel.HotelBody
import com.robosoft.foursquare.model.dataclass.hotel.HotelResponse
import com.robosoft.foursquare.model.network.ProjectApi
import com.robosoft.foursquare.model.network.ServiceBuilderObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavouriteViewModel: ViewModel() {

    private val retrofit = ServiceBuilderObject.buildService(ProjectApi::class.java)

    private var FavouriteLiveDataList: MutableLiveData<HotelResponse?> = MutableLiveData()

    fun getFavouriteLiveDataObserver(): MutableLiveData<HotelResponse?> {
        return FavouriteLiveDataList
    }

    fun getFavourite(accessToken: String,data: HotelBody) {
        retrofit.getFavourite(accessToken, data).enqueue(object : Callback<HotelResponse> {
            override fun onFailure(call: Call<HotelResponse>, t: Throwable) {
                FavouriteLiveDataList.postValue(null)
                Log.d("topPick response",t.toString())
            }
            override fun onResponse(
                call: Call<HotelResponse>,
                response: Response<HotelResponse>
            ) {
                FavouriteLiveDataList.postValue(response.body())
                Log.d("getFav response", response.toString())
            }
        })
    }

}