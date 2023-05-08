package com.example.travelfeeldog.presentation.location

import android.os.Bundle
import android.view.View
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentLocationDetailBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigate

class LocationDetailFragment : BaseFragment<FragmentLocationDetailBinding>(R.layout.fragment_location_detail) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
    }

}