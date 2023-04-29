package com.example.travelfeeldog.presentation.signin

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.ActivitySignInBinding
import com.example.travelfeeldog.presentation.common.BaseActivity

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }
}