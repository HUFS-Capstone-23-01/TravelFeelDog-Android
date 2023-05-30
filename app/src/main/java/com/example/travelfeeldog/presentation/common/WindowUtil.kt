package com.example.travelfeeldog.presentation.common

import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

object WindowUtil {

    fun setWindow(context: FragmentActivity, statusBarColor: Int, navigationBarColor: Int) {
        context.window.statusBarColor = ContextCompat.getColor(
            context,
            statusBarColor
        )

        context.window.navigationBarColor = ContextCompat.getColor(
            context, navigationBarColor
        )
    }

    fun hideKeyboard(context: FragmentActivity) {
        val imm: InputMethodManager =
            context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(context.currentFocus?.windowToken, 0)
    }
}