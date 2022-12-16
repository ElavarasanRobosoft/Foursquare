package com.robosoft.foursquare.model.network

import android.util.Log
import com.robosoft.foursquare.model.dataclass.signin.SignInBody
import com.robosoft.foursquare.model.dataclass.signin.SignInResponse
import com.robosoft.foursquare.model.dataclass.signup.SignUpBody
import com.robosoft.foursquare.model.dataclass.signup.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectService {

    private val retrofit = ServiceBuilderObject.buildService(ProjectApi::class.java)

    fun signIn(data: SignInBody, onResult: (SignInResponse?) -> Unit) {
        retrofit.signIn(data).enqueue(object : Callback<SignInResponse> {
            override fun onResponse(
                call: Call<SignInResponse>,
                response: Response<SignInResponse>
            ) {
                Log.d("response", response.toString())
                onResult(response.body()!!)
            }

            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun signUn(data: SignUpBody, onResult: (SignUpResponse?) -> Unit) {
        retrofit.signUp(data).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                Log.d("response", response.toString())
                onResult(response.body()!!)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }
}