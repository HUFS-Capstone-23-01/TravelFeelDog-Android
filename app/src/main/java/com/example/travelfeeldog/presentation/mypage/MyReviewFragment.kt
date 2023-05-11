package com.example.travelfeeldog.presentation.mypage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.lifecycle.ViewModel
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentMyReviewBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.LoadingUtil
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigateUp
import com.example.travelfeeldog.presentation.mypage.adapter.MyReviewAdapter
import com.example.travelfeeldog.presentation.mypage.viewmodel.MyPageViewModel
import com.example.travelfeeldog.util.UserInfo
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MyReviewFragment : BaseFragment<FragmentMyReviewBinding>(R.layout.fragment_my_review) {

    private val viewModel: MyPageViewModel by sharedViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMyReviewList(UserInfo.getUserInfo()!!.token)
        LoadingUtil.showTaskProgressAnimation(binding.lavLoading)

        binding.rvMyReviewList.adapter = MyReviewAdapter(viewModel).apply {
            viewModel.myReviewList.observe(viewLifecycleOwner) { reviewList ->
                LoadingUtil.cancelTaskProgressAnimation(binding.lavLoading)
                submitList(reviewList)
            }
        }

        binding.ibMyPageReviewClose.setOnClickListener {
            navigateUp()
        }
    }


}