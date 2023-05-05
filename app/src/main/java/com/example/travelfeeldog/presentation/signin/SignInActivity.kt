package com.example.travelfeeldog.presentation.signin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.example.travelfeeldog.BuildConfig
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.ActivitySignInBinding
import com.example.travelfeeldog.presentation.common.BaseActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in){
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}