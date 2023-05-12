package com.example.travelfeeldog.presentation.review

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.PopupMenu
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentReviewBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.LoadingUtil
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigateUp
import com.example.travelfeeldog.presentation.mypage.adapter.MyReviewAdapter
import com.example.travelfeeldog.presentation.place.viewmodel.PlaceViewModel
import com.example.travelfeeldog.presentation.review.adapter.PlaceReviewAdapter
import com.example.travelfeeldog.util.EventObserver
import com.example.travelfeeldog.util.UserInfo
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class ReviewFragment : BaseFragment<FragmentReviewBinding>(R.layout.fragment_review) {

    private val placeViewModel: PlaceViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        placeViewModel.getPlaceReview(UserInfo.getUserInfo()!!.token)

        binding.rvReviewContainer.adapter = PlaceReviewAdapter(placeViewModel).apply {
            placeViewModel.placeReview.observe(viewLifecycleOwner, EventObserver { reviewList ->
                submitList(reviewList)
            })
        }

        binding.ibReviewClose.setOnClickListener {
            navigateUp()
        }

        binding.cgOptionContainer.setOnCheckedStateChangeListener { group, checkedIds ->
            val checkedId = group.checkedChipId
            val checkedChip = requireActivity().findViewById<Chip>(checkedId)
            val checkedOption = checkedChip.text.toString()
            Timber.d("선택된 검색 옵션 : $checkedOption")
        }
    }

}