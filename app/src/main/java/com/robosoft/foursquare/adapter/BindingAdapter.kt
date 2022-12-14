package com.robosoft.foursquare.adapter

import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("app:textWatcher")
fun watcher(view: EditText, passwordTextWatcher: TextWatcher){
    view.addTextChangedListener(passwordTextWatcher)
}
