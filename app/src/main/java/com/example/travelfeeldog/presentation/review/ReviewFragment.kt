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
import com.example.travelfeeldog.util.UserInfo
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ReviewFragment : BaseFragment<FragmentReviewBinding>(R.layout.fragment_review), PopupMenu.OnMenuItemClickListener {

    private val placeViewModel: PlaceViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        placeViewModel.getPlaceReview(UserInfo.getUserInfo()!!.token, 2)

        binding.rvReviewContainer.adapter = PlaceReviewAdapter(placeViewModel).apply {
            placeViewModel.placeReview.observe(viewLifecycleOwner) { reviewList ->
                submitList(reviewList)
            }
        }

        binding.ibReviewClose.setOnClickListener {
            navigateUp()
        }

        binding.btnReviewSearchOption.setOnClickListener {
            showPopup(it)
        }
    }

    private fun showPopup(v: View) {
        val popup = PopupMenu(requireActivity(), v, Gravity.END)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_review_search, popup.menu)
        popup.setOnMenuItemClickListener(this)
        popup.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.option_date-> {
                Log.d("clickMenu", "date")
                true
            }
            R.id.option_like -> {
                Log.d("clickMenu", "like")
                true
            }
            R.id.option_normal -> {
                Log.d("clickMenu", "normal")
                true
            }
            R.id.option_dislike -> {
                Log.d("clickMenu", "dislike")
                true
            }
            else -> false
        }
    }

}