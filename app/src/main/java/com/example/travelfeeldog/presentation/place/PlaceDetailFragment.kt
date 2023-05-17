package com.example.travelfeeldog.presentation.place

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentLocationDetailBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigate
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigateUp
import com.example.travelfeeldog.presentation.place.viewmodel.PlaceViewModel
import com.example.travelfeeldog.util.UserInfo
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PlaceDetailFragment :
    BaseFragment<FragmentLocationDetailBinding>(R.layout.fragment_location_detail) {

    private val placeViewModel: PlaceViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.statusBarColor = ContextCompat.getColor(
            requireActivity(),
            R.color.transparent
        )
        placeViewModel.getPlaceInfo(UserInfo.getUserInfo()!!.token)

        placeViewModel.placeInfo.observe(viewLifecycleOwner) { placeInfo ->
            binding.placeInfo = placeInfo
        }

        binding.clLikeStaticsArea.setOnClickListener {
            navigate(R.id.action_locationDetailFragment_to_positiveEvaluationFragment)
        }

        binding.clBadStaticsArea.setOnClickListener {
            navigate(R.id.action_locationDetailFragment_to_negativeEvaluationFragment)
        }

        binding.btnRequestReview.setOnClickListener {
            navigate(R.id.action_locationDetailFragment_to_placeReviewFragment)
        }

        binding.tvMoveReviewPage.setOnClickListener {
            navigate(R.id.action_locationDetailFragment_to_reviewFragment)
        }

        binding.tbTopMenu.setNavigationOnClickListener { navigateUp() }
    }
}