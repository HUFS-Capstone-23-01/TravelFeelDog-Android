package com.example.travelfeeldog.presentation.signin

import android.os.Bundle
import android.view.View
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentSignInBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.NavigationUtil.navigate
import com.example.travelfeeldog.util.TestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : BaseFragment<FragmentSignInBinding>(R.layout.fragment_sign_in) {

    private val viewModel : TestViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.setOnClickListener {
            navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

}