package com.example.travelfeeldog.presentation.common

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
}