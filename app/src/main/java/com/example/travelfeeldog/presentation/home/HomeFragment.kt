package com.example.travelfeeldog.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentHomeBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.CustomSnackBar
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigate
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigateWithBundle
import com.example.travelfeeldog.presentation.common.navigation.OnRequestNavigateNotBottomViewListener
import com.example.travelfeeldog.presentation.home.adapter.EventBannerAdapter
import com.example.travelfeeldog.presentation.home.adapter.MostReviewPlaceAdapter
import com.example.travelfeeldog.presentation.home.adapter.PopularPlaceAdapter
import com.example.travelfeeldog.presentation.home.item.EventBanner
import com.example.travelfeeldog.presentation.home.item.EventBannerBody
import com.example.travelfeeldog.presentation.home.viewmodel.HomeViewModel
import com.example.travelfeeldog.presentation.place.viewmodel.PlaceViewModel
import com.example.travelfeeldog.presentation.search.viewmodel.SearchViewModel
import com.example.travelfeeldog.util.Constants
import com.example.travelfeeldog.util.EventObserver
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val placeViewModel: PlaceViewModel by sharedViewModel()
    private val homeViewModel: HomeViewModel by sharedViewModel()
    private val searchViewModel: SearchViewModel by sharedViewModel()
    private var navigateListener: OnRequestNavigateNotBottomViewListener? = null
    private var selectedLocationOption: String = Constants.defaultLocation

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnRequestNavigateNotBottomViewListener) {
            navigateListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        searchViewModel.setCategoryName(Constants.defaultCategory)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestRecommendPlace()
        setLocationOptionEvent()

        placeViewModel.isClickedPlaceItem.observe(viewLifecycleOwner, EventObserver { isClicked ->
            if(isClicked) {
                navigate(R.id.action_nav_home_to_locationDetailFragment)
            }
        })

        binding.rvFavoriteLocationContainer.adapter = PopularPlaceAdapter(placeViewModel).apply {
            homeViewModel.popularPlace.observe(viewLifecycleOwner, EventObserver { popularPlace ->
                Timber.d("인기 장소를 불러오는 데 성공했습니다 : $popularPlace")
                submitList(popularPlace)
            })
        }

        binding.rvReviewSortedContainer.adapter = MostReviewPlaceAdapter(placeViewModel).apply {
            homeViewModel.mostReviewPlace.observe(viewLifecycleOwner, EventObserver { mostReviewPlace ->
                Timber.d("리뷰 많은 장소를 불러오는 데 성공했습니다 : ${mostReviewPlace}")
                submitList(mostReviewPlace)
            })
        }

        val eventBannerInfo = EventBanner().getBannerList()
        setHomeEventBanner(eventBannerInfo, 2)

        binding.tvHomeSearchBarHint.setOnClickListener {
            navigateListener?.onRequestNavigate(R.id.nav_search)
        }

        binding.clSearchBotBanner.setOnClickListener {
            navigate(R.id.action_nav_home_to_gptSearchFragment)
        }

        // -------------------- 카테고리 선택 관리 --------------------

        binding.ibHomeCategoryLodging.setOnClickListener {
            searchViewModel.setCategoryName(binding.tvHomeCategoryLodgingText.text.toString())
            navigateListener?.onRequestNavigate(R.id.nav_search)

        }

        binding.ibHomeCategoryWalking.setOnClickListener {
            searchViewModel.setCategoryName(binding.tvHomeCategoryWalkingText.text.toString())
            navigateListener?.onRequestNavigate(R.id.nav_search)

        }

        binding.ibHomeCategoryCafeFood.setOnClickListener {
            searchViewModel.setCategoryName(binding.tvHomeCategoryCafeFood.text.toString())
            navigateListener?.onRequestNavigate(R.id.nav_search)
        }
    }

    // -------------------- 이벤트 배너 관리 --------------------

    private fun mainViewChangeEvent(maxEvent: Int) {
        binding.vpAdBoard.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.vAdIndicator.text = "${position + 1} / ${maxEvent}"
            }
        })
    }

    private fun setHomeEventBanner(itemList: MutableList<EventBannerBody>, maxEvent: Int) {
        binding.vpAdBoard.apply {
            binding.vAdIndicator.text = "1 / ${itemList.size}"
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 1
            adapter = EventBannerAdapter(requireActivity(), itemList)
        }
        mainViewChangeEvent(maxEvent)
    }

    // -------------------- 지역 옵션 관리 --------------------

    private fun setLocationOptionEvent() {
        binding.cgHomeLocationGroup.setOnCheckedStateChangeListener { group, _ ->
            val checkedChip = getCheckedChip(group.checkedChipId)
            initLocationOption(checkedChip)
            Timber.d("선택된 지역 옵션 : $selectedLocationOption")
        }
    }

    private fun getCheckedChip(checkedId: Int): Chip {
        return requireActivity().findViewById(checkedId) as Chip
    }

    private fun initLocationOption(checkedChip: Chip) {
        selectedLocationOption = checkedChip.text.toString()
        requestRecommendPlace()
    }

    private fun requestRecommendPlace() {
        homeViewModel.getPopularPlace("숙소", selectedLocationOption)
        homeViewModel.getMostReviewPlace(selectedLocationOption)
    }

    override fun onDetach() {
        super.onDetach()
        navigateListener = null
    }
}