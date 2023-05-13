package com.example.travelfeeldog.presentation.common

import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.ItemSnackBarBinding
import com.google.android.material.snackbar.Snackbar

class CustomSnackBar(view: View, private val message: String, private val drawable: Drawable) {

//    val drawable = ContextCompat.getDrawable(this, R.drawable.ic_check)!!
//    SampleSnackBar.make(binding.root, "String", drawable).show()

    companion object {
        fun make(view: View, message: String, drawable: Drawable) =
            CustomSnackBar(view, message, drawable)
    }

    private val context = view.context
    private val snackBar = Snackbar.make(view, "", 2000)
    private val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout

    private val inflater = LayoutInflater.from(context)
    private val snackBarBinding: ItemSnackBarBinding =
        DataBindingUtil.inflate(inflater, R.layout.item_snack_bar, null, false)

    init {
        initView()
        initData()
    }

    private fun initView() {
        with(snackBarLayout) {
            val layoutParams = layoutParams as FrameLayout.LayoutParams

            val snackBarShowAnim = AnimationUtils.loadAnimation(context, R.anim.show_snack_bar)
            val snackBarHideAnim = AnimationUtils.loadAnimation(context, R.anim.hide_snack_bar)
            this.startAnimation(snackBarShowAnim)

            Handler(Looper.getMainLooper()).postDelayed({
                this.startAnimation(snackBarHideAnim)
            }, 80000L)

            layoutParams.gravity = Gravity.TOP
            removeAllViews()
            setPadding(0, 16, 0, 0)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            addView(snackBarBinding.root, 0)
        }
    }

    private fun initData() {
        snackBarBinding.tvText.text = message
        snackBarBinding.ivIcon.background = drawable
    }

    fun show() {
        snackBar.show()
    }
}

