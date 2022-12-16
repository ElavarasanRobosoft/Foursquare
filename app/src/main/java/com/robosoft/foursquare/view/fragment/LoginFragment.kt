package com.robosoft.foursquare.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.robosoft.foursquare.R
import com.robosoft.foursquare.databinding.FragmentLoginBinding
import com.robosoft.foursquare.model.dataclass.signin.SignInBody
import com.robosoft.foursquare.model.network.ProjectApi
import com.robosoft.foursquare.model.network.ProjectService
import com.robosoft.foursquare.view.activity.HomeActivity
import java.util.regex.Pattern

class LoginFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding
    private val projectApi = ProjectService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)

        val email = loginBinding.emailEt.text.toString()
        val password = loginBinding.passwordEt.text.toString()
        loginBinding.loginBtn.isClickable

        loginBinding.createAccountTv.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fragment_container,
                    RegistrationFragment()
                )
                ?.addToBackStack(null)
                ?.commit()
        }

        loginBinding.forgetPasswordTv.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fragment_container,
                    OtpFragment()
                )
                ?.addToBackStack(null)
                ?.commit()
        }

        loginBinding.loginBtn.setOnClickListener {
            !loginBinding.loginBtn.isClickable
            if (validateEmail(email)) {
                if (validatePassword(password)) {
                    val data = SignInBody(email, password)
                    projectApi.signIn(data) {
                        if (it != null) {
                            when (it.message) {
                                "Login successful" -> {
                                    Log.d("sign in response", it.toString())
                                    Log.d("accessToken", it.access_token)
                                    val sharedPreferences =
                                        activity?.applicationContext?.getSharedPreferences(
                                            "sharedPreference",
                                            Context.MODE_PRIVATE
                                        )
                                    val editor = sharedPreferences?.edit()
                                    editor?.putString("accessToken", it.access_token)
                                    editor?.apply()
                                    activity?.startActivity(
                                        Intent(
                                            activity,
                                            HomeActivity::class.java
                                        )
                                    )
                                }
                                "No user found" -> {
                                    Toast.makeText(
                                        activity?.applicationContext,
                                        "Invalid Email Id",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                else -> {
                                    Toast.makeText(
                                        activity?.applicationContext,
                                        "Invalid Password",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        } else {
                            Toast.makeText(
                                activity?.applicationContext,
                                "Something went wrong",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        activity?.applicationContext,
                        "Password must contains atleast one capital letter,small letter,special character,number",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(activity?.applicationContext, "Invalid Email ID", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return loginBinding.root
    }

    private fun validateEmail(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private fun validatePassword(password: String): Boolean {
        val pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$")
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }
}