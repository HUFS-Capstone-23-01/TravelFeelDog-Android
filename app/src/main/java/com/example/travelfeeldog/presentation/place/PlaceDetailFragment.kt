package com.example.travelfeeldog.presentation.place

import android.os.Bundle
import android.view.View
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentLocationDetailBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigate
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigateUp
import com.example.travelfeeldog.presentation.place.viewmodel.PlaceViewModel
import com.example.travelfeeldog.util.UserInfo
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class PlaceDetailFragment : BaseFragment<FragmentLocationDetailBinding>(R.layout.fragment_location_detail) {

    private val placeViewModel : PlaceViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO("홈, 검색 구현 이전 임시적으로 장소 아이디 설정, 원래는 이 페이지로 이동하기 전에 장소 아이디를 저장시켜야 함 -> 추후 수정")
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

        binding.ivBackBtn.setOnClickListener{
            navigateUp()
        }
    }
}