package com.example.travelfeeldog.presentation.signin

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.example.travelfeeldog.BuildConfig
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentSignInBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.LoadingUtil
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigate
import com.example.travelfeeldog.presentation.signin.viewmodel.AuthViewModel
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userLiveData.observe(viewLifecycleOwner) { userInfo ->
            viewModel.getTokenValid(userInfo.uid)
        }

        viewModel.isVerifiedUser.observe(viewLifecycleOwner) { isVerified ->
            if(isVerified) {
                navigate(R.id.action_signInFragment_to_mainActivity)
            } else {
                navigate(R.id.action_signInFragment_to_signUpFragment)
            }
        }

        binding.btnSignIn.setOnClickListener {
            // TODO(멤버 API 완성되면 코드 수정)
            navigate(R.id.action_signInFragment_to_mainActivity)
//            googleLogInRequest.launch(googleSignInClient.signInIntent)
        }

        val googleLogInRequest =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                    try {
                        val account = task.getResult(ApiException::class.java)!!
                        viewModel.getAuthTokenFromFirebase(account.idToken!!)
                        LoadingUtil.showTaskProgressAnimation(binding.lavLoading)
                    } catch (e: ApiException) {
                        Timber.d(e)
                    }
                }
            }
    }
}