package com.example.travelfeeldog.presentation.signin

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.example.travelfeeldog.BuildConfig
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentSignInBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.LoadingUtil
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigate
import com.example.travelfeeldog.presentation.signin.viewmodel.AuthViewModel
import com.example.travelfeeldog.util.EventObserver
import com.example.travelfeeldog.util.TestViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SignInFragment : BaseFragment<FragmentSignInBinding>(R.layout.fragment_sign_in) {

    private val viewModel: AuthViewModel by sharedViewModel()
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        viewModel.userLiveData.observe(viewLifecycleOwner, EventObserver { userInfo ->
            viewModel.tryToSignInByAuth(userInfo.uid)
        })

        viewModel.isVerifiedUser.observe(viewLifecycleOwner, EventObserver { isVerified ->
            LoadingUtil.cancelTaskProgressAnimation(binding.lavLoading)
            if(isVerified) {
                navigate(R.id.action_signInFragment_to_mainActivity)
            } else {
                navigate(R.id.action_signInFragment_to_signUpFragment)
            }
        })

        val googleLogInRequest =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                    try {
                        val account = task.getResult(ApiException::class.java)!!
                        handleSignInResult(account.idToken!!)
                        LoadingUtil.showTaskProgressAnimation(binding.lavLoading)
                    } catch (e: ApiException) {
                        Timber.d(e)
                    }
                }
            }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID)
            .requestEmail()
            .build()

        binding.btnSignIn.setOnClickListener {
            googleLogInRequest.launch(GoogleSignIn.getClient(requireActivity(), gso).signInIntent)
        }
    }

    private fun handleSignInResult(googleIdToken: String) {
        val credential = GoogleAuthProvider.getCredential(googleIdToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful) {
                viewModel.handleAuthToken(auth.currentUser!!)
            } else {
                Timber.d("FAIL: get Auth token from firebase")
            }
        }
    }

}