package com.example.travelfeeldog.presentation.mypage

import android.os.Bundle
import android.view.View
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentMyPageBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigate
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        binding.clReviewArea.setOnClickListener {
            navigate(R.id.action_nav_my_page_to_myReviewFragment)
        }

        binding.btnLogout.setOnClickListener {
            displayConfirmMessage()
        }
    }

    private fun displayConfirmMessage() {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle(resources.getString(R.string.logout_title))
            .setMessage(resources.getString(R.string.logout_message))
            .setNegativeButton(resources.getString(R.string.logout_decline)) { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.logout_accept)) { dialog, which ->
                logout()
            }
            .show()
    }

    private fun logout() {
        Firebase.auth.signOut()
        mGoogleSignInClient.signOut().addOnCompleteListener(requireActivity()) { signOut ->
            if (signOut.isSuccessful) {
                navigate(R.id.action_nav_my_page_to_signInActivity)
            }
        }
    }
}