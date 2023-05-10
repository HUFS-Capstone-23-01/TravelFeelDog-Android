package com.example.travelfeeldog.presentation.signin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentSignUpBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.LoadingUtil
import com.example.travelfeeldog.presentation.common.bindingadapter.BindingAdapter
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigate
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigateUp
import com.example.travelfeeldog.presentation.signin.viewmodel.AuthViewModel
import com.example.travelfeeldog.util.EventObserver
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {

    private val viewModel: AuthViewModel by sharedViewModel()
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        binding.viewModel = viewModel

        viewModel.isNicknameDuplicated.observe(viewLifecycleOwner) { isNicknameDuplicated ->
            binding.divCurtain.visibility = View.INVISIBLE
            binding.isNicknameDuplicated = isNicknameDuplicated
        }

        viewModel.isSignedUp.observe(viewLifecycleOwner) { isSignedUp ->
            if(isSignedUp) {
                navigate(R.id.action_signUpFragment_to_mainActivity)
                requireActivity().finish()
            } else {
                Toast.makeText(requireActivity(), "다시 시도해 주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSingUpDone.setOnClickListener {
            viewModel.postMember(binding.etvInputBox.text.toString())
        }

        binding.tvDuplicateVerification.setOnClickListener {
            viewModel.checkNicknameDuplicated(binding.etvInputBox.text.toString())
        }

        binding.ibSignUpClose.setOnClickListener {
            logout()
        }

        binding.etvInputBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                handleSignUpButtonState(binding.btnSingUpDone, false)
                BindingAdapter.setVisibility(binding.tvNotificationCorrectInput, false)
                BindingAdapter.setVisibility(binding.tvErrorDuplication, false)
                BindingAdapter.setVisibility(binding.tvErrorNotValidInput, false)
            }
            override fun afterTextChanged(text: Editable?) {
                viewModel.checkValidNickname(text.toString())
            }
        })
    }

    private fun logout() {
        Firebase.auth.signOut()
        mGoogleSignInClient.signOut().addOnCompleteListener(requireActivity()) { signOut ->
            if (signOut.isSuccessful) {
                navigateUp()
            }
        }
    }

    private fun handleSignUpButtonState(view: Button, state: Boolean) {
        BindingAdapter.setSolidButtonStateUi(view, state)
        BindingAdapter.setEnabled(view, state)
    }

}