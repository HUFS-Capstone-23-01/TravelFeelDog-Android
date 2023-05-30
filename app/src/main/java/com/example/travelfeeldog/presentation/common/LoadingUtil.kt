package com.example.travelfeeldog.presentation.common

import android.view.View
import com.airbnb.lottie.LottieAnimationView

object LoadingUtil {

    fun showTaskProgressAnimation(loadingView: LottieAnimationView) {
        loadingView.visibility = View.VISIBLE
        loadingView.playAnimation()
    }
    fun cancelTaskProgressAnimation(loadingView: LottieAnimationView) {
        loadingView.cancelAnimation()
        loadingView.visibility = View.GONE
    }
}