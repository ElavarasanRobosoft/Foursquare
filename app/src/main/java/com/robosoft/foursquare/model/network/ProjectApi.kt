package com.robosoft.foursquare.model.network

import com.robosoft.foursquare.model.dataclass.signin.SignInBody
import com.robosoft.foursquare.model.dataclass.signin.SignInResponse
import com.robosoft.foursquare.model.dataclass.signup.SignUpBody
import com.robosoft.foursquare.model.dataclass.signup.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ProjectApi {

    @Headers("Content-Type: application/json")
    @POST("/signIn")
    fun signIn(@Body data : SignInBody) : Call<SignInResponse>

    @Headers("Content-Type: application/json")
    @POST("/signUn")
    fun signUp(@Body data : SignUpBody) : Call<SignUpResponse>

}