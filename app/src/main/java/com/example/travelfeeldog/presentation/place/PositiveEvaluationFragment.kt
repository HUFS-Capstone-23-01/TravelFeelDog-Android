package com.example.travelfeeldog.presentation.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentPositiveEvaluationBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigateUp
import com.example.travelfeeldog.presentation.place.adapter.PlaceKeywordStatisticsAdapter
import com.example.travelfeeldog.presentation.place.viewmodel.PlaceViewModel
import com.example.travelfeeldog.util.EventObserver
import com.example.travelfeeldog.util.UserInfo
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PositiveEvaluationFragment : BaseFragment<FragmentPositiveEvaluationBinding>(R.layout.fragment_positive_evaluation) {

    private val placeViewModel: PlaceViewModel by sharedViewModel()
    private val args: PositiveEvaluationFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        placeViewModel.getPlaceKeywordStatistics(UserInfo.getUserInfo()!!.token, args.evaluation)

        binding.rvStaticsList.adapter = PlaceKeywordStatisticsAdapter(args.evaluation).apply {
            placeViewModel.placeKeywordStatistics.observe(viewLifecycleOwner, EventObserver { keywordStatistics ->
                submitList(keywordStatistics)
            })
        }

        binding.ibPageClose.setOnClickListener {
            navigateUp()
        }
    }
}