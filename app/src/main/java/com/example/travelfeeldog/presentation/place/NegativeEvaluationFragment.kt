package com.example.travelfeeldog.presentation.place

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentNegativeEvaluationBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigateUp
import com.example.travelfeeldog.presentation.place.adapter.PlaceKeywordStatisticsAdapter
import com.example.travelfeeldog.presentation.place.viewmodel.PlaceViewModel
import com.example.travelfeeldog.util.EventObserver
import com.example.travelfeeldog.util.UserInfo
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NegativeEvaluationFragment : BaseFragment<FragmentNegativeEvaluationBinding>(R.layout.fragment_negative_evaluation) {

    private val placeViewModel: PlaceViewModel by sharedViewModel()
    private val args: NegativeEvaluationFragmentArgs by navArgs()
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