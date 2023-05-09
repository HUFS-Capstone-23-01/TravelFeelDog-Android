package com.example.travelfeeldog.presentation.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.ActivitySignInBinding
import com.example.travelfeeldog.presentation.MainActivity
import com.example.travelfeeldog.presentation.common.BaseActivity
import com.example.travelfeeldog.presentation.signin.viewmodel.AuthViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in){

    private val viewModel: AuthViewModel by viewModel()

    override fun onStart() {
        super.onStart()
        val currentUser = Firebase.auth.currentUser
        if(currentUser != null) {
//            TODO("멤버 API 완성되면 해당 코드 수정")
//            viewModel.getTokenValid(currentUser.uid)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        viewModel.isVerifiedUser.observe(this) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}