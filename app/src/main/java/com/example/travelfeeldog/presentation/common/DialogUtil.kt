package com.example.travelfeeldog.presentation.common

import android.content.Context
import com.example.travelfeeldog.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogUtil {

    fun getDialogLogout(context: Context): MaterialAlertDialogBuilder {
        return MaterialAlertDialogBuilder(context)
            .setTitle(context.resources.getString(R.string.logout_title))
            .setMessage(context.resources.getString(R.string.logout_message))
            .setNegativeButton(context.resources.getString(R.string.logout_decline)) { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton(context.resources.getString(R.string.logout_accept)) { dialog, which ->

            }
    }
}