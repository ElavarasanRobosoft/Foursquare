package com.robosoft.foursquare.viewmodel

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    var passwordTextWatcher: TextWatcher = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            Log.d("password",p0.toString())
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }

    fun onClickSubmit(view: View) {

    }

    fun onClickForgetPassword(view: View) {

    }

    fun onClickCreateAccount(view: View) {

    }
}