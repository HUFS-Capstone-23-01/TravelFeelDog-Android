package com.example.travelfeeldog.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentHomeBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigate
import com.example.travelfeeldog.presentation.common.navigation.OnRequestNavigateNotBottomViewListener
import com.example.travelfeeldog.presentation.home.adapter.EventBannerAdapter
import com.example.travelfeeldog.presentation.home.item.EventBanner
import com.example.travelfeeldog.presentation.place.viewmodel.PlaceViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val placeViewModel: PlaceViewModel by sharedViewModel()
    private var navigateListener: OnRequestNavigateNotBottomViewListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnRequestNavigateNotBottomViewListener) {
            navigateListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO(고정된 정보 - 나중에 고정된 정보끼리 데이터 셋 만들 것)
        val eventBannerInfo = mutableListOf<EventBanner>().apply {
            add(
                EventBanner(
                    R.drawable.bg_gradient_red_pinkpurple,
                    R.drawable.img_3d_package,
                    R.string.first_sub_title,
                    R.string.first_main_title
                )
            )
            add(
                EventBanner(
                    R.drawable.bg_gradient_blue_purple,
                    R.drawable.ic_3d_discount,
                    R.string.second_sub_title,
                    R.string.second_main_title
                )
            )
        }

        binding.tvHomeSearchBarHint.setOnClickListener {
            navigateListener?.onRequestNavigate(R.id.nav_search)
        }

        //TODO("장소 상세 페이지 테스트를 위해 임시적으로 제작한 코드 -> 추후 삭제")
        binding.ibHomeCategoryLodging.setOnClickListener {
            placeViewModel.setRequestPlace(1)
            navigate(R.id.action_nav_home_to_locationDetailFragment)
        }

        setHomeEventBanner(eventBannerInfo, 2)
    }

    private fun mainViewChangeEvent(maxEvent: Int) {
        binding.vpAdBoard.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.vAdIndicator.text = "${position + 1} / ${maxEvent}"
            }
        })
    }

    private fun setHomeEventBanner(itemList: MutableList<EventBanner>, maxEvent: Int) {
        binding.vpAdBoard.apply {
            binding.vAdIndicator.text = "1 / ${itemList.size}"
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 1
            adapter = EventBannerAdapter(requireActivity(), itemList)
        }
        mainViewChangeEvent(maxEvent)
    }

    override fun onDetach() {
        super.onDetach()
        navigateListener = null
    }
}