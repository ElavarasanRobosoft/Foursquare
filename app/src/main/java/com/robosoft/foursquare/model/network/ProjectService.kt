package com.robosoft.foursquare.model.network

import android.util.Log
import com.robosoft.foursquare.model.dataclass.ChangePasswordBody
import com.robosoft.foursquare.model.dataclass.NameResponse
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
                Log.d("signin response", response.toString())
                onResult(response.body()!!)
            }

            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun signUp(data: SignUpBody, onResult: (SignUpResponse?) -> Unit) {
        retrofit.signUp(data).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                Log.d("signup response", response.toString())
                onResult(response.body())
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun forgetPassword(data: ForgetPasswordBody, onResult: (ResponseMessage?) -> Unit) {
        retrofit.forgetPassword(data).enqueue(object : Callback<ResponseMessage> {
            override fun onResponse(
                call: Call<ResponseMessage>,
                response: Response<ResponseMessage>
            ) {
                Log.d("forget password response", response.toString())
                onResult(response.body())
            }

            override fun onFailure(call: Call<ResponseMessage>, t: Throwable) {
                onResult(null)
            }
        })
    }
    fun verifyOtp(data: VerifyOtpBody, onResult: (ResponseMessage?) -> Unit) {
        retrofit.verifyOtp(data).enqueue(object : Callback<ResponseMessage> {
            override fun onResponse(
                call: Call<ResponseMessage>,
                response: Response<ResponseMessage>
            ) {
                Log.d("verify otp response", response.toString())
                onResult(response.body())
            }

            override fun onFailure(call: Call<ResponseMessage>, t: Throwable) {
                onResult(null)
            }
        })
    }
    fun resendOtp(data: ForgetPasswordBody, onResult: (ResponseMessage?) -> Unit) {
        retrofit.resendOtp(data).enqueue(object : Callback<ResponseMessage> {
            override fun onResponse(
                call: Call<ResponseMessage>,
                response: Response<ResponseMessage>
            ) {
                Log.d("resendOtp response", response.toString())
                onResult(response.body())
            }

            override fun onFailure(call: Call<ResponseMessage>, t: Throwable) {
                onResult(null)
            }
        })
    }
    fun changePassword(data: ChangePasswordBody, onResult: (ResponseMessage?) -> Unit) {
        retrofit.changePassword(data).enqueue(object : Callback<ResponseMessage> {
            override fun onResponse(
                call: Call<ResponseMessage>,
                response: Response<ResponseMessage>
            ) {
                Log.d("changePassword response", response.toString())
                onResult(response.body())
            }

            override fun onFailure(call: Call<ResponseMessage>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun aboutUS(onResult: (ResponseMessage?) -> Unit) {
        retrofit.aboutUS().enqueue(object : Callback<ResponseMessage> {
            override fun onResponse(
                call: Call<ResponseMessage>,
                response: Response<ResponseMessage>
            ) {
                Log.d("aboutUS response", response.toString())
                onResult(response.body())
            }

            override fun onFailure(call: Call<ResponseMessage>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun getName(access_token: String, onResult: (NameResponse?) -> Unit) {

        retrofit.getName(access_token).enqueue(object : Callback<NameResponse> {
            override fun onResponse(
                call: Call<NameResponse>,
                response: Response<NameResponse>
            ) {
                onResult(response.body())
            }

            override fun onFailure(call: Call<NameResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }

}