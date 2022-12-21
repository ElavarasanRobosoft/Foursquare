package com.robosoft.foursquare.model.network

import com.robosoft.foursquare.model.dataclass.ChangePasswordBody
import com.robosoft.foursquare.model.dataclass.ResponseMessage
import com.robosoft.foursquare.model.dataclass.VerifyOtpBody
import com.robosoft.foursquare.model.dataclass.forgetpassword.ForgetPasswordBody
import com.robosoft.foursquare.model.dataclass.hotel.HotelBody
import com.robosoft.foursquare.model.dataclass.hotel.HotelResponse
import com.robosoft.foursquare.model.dataclass.individualhotel.getParticularPlaceDetailsBody
import com.robosoft.foursquare.model.dataclass.individualhotel.getParticularPlaceDetailsResponse
import com.robosoft.foursquare.model.dataclass.signin.SignInBody
import com.robosoft.foursquare.model.dataclass.signin.SignInResponse
import com.robosoft.foursquare.model.dataclass.signup.SignUpBody
import com.robosoft.foursquare.model.dataclass.signup.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ProjectApi {

    @Headers("Content-Type: application/json")
    @POST("/signIn")
    fun signIn(@Body data : SignInBody) : Call<SignInResponse>

    @Headers("Content-Type: application/json")
    @POST("/signUp")
    fun signUp(@Body data : SignUpBody) : Call<SignUpResponse>

    @Headers("Content-Type: application/json")
    @POST("/forgotPassword")
    fun forgetPassword(@Body data : ForgetPasswordBody) : Call<ResponseMessage>

    @Headers("Content-Type: application/json")
    @POST("/verify")
    fun verifyOtp(@Body data : VerifyOtpBody) : Call<ResponseMessage>

    @Headers("Content-Type: application/json")
    @POST("/resendOtp")
    fun resendOtp(@Body data : ForgetPasswordBody) : Call<ResponseMessage>

    @Headers("Content-Type: application/json")
    @POST("/forgotPassword/createNewPassword")
    fun changePassword(@Body data : ChangePasswordBody) : Call<ResponseMessage>

    @Headers("Content-Type: application/json")
    @POST("/getNearByPlaces")
    fun getNearByPlaces(@Body data : HotelBody) : Call<HotelResponse>

    @Headers("Content-Type: application/json")
    @POST("/getParticularPlaceDetails")
    fun getParticularPlaceDetails(@Body data : getParticularPlaceDetailsBody) : Call<getParticularPlaceDetailsResponse>

}