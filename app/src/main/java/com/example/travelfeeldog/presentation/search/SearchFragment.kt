package com.example.travelfeeldog.presentation.search

import android.os.Bundle
import android.text.InputType
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentSearchBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigate
import com.example.travelfeeldog.presentation.place.viewmodel.PlaceViewModel
import com.example.travelfeeldog.presentation.search.adapter.PlaceSearchResultAdapter
import com.example.travelfeeldog.presentation.search.viewmodel.SearchViewModel
import com.example.travelfeeldog.util.Constants
import com.example.travelfeeldog.util.EventObserver
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val placeViewModel: PlaceViewModel by sharedViewModel()
    private val searchViewModel: SearchViewModel by sharedViewModel()
    private var selectedCategoryOption: String = Constants.defaultCategory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        handleSelectedLocationOption()
        handleUserInput()

        searchViewModel.isHome.observe(viewLifecycleOwner,EventObserver {
            if(it) {
                Timber.d("홈에서 왔음")
            }
        })

        searchViewModel.isValidOptionSet.observe(viewLifecycleOwner, EventObserver { isValidRequest ->
            if(isValidRequest) {
                searchViewModel.getSearchResult()
            }
        })

        searchViewModel.categoryName.observe(viewLifecycleOwner) {
            Timber.d("카테고리 값 변경 ${it}")
            initCheckedCategory()
            handleCategoryOptionEvent()
        }

        placeViewModel.isClickedPlaceItem.observe(viewLifecycleOwner, EventObserver { isRequested ->
            if(isRequested) {
                navigate(R.id.action_nav_search_to_locationDetailFragment)
            }
        })

        binding.rvSearchResultContainer.adapter = PlaceSearchResultAdapter(placeViewModel).apply {
            searchViewModel.searchResult.observe(viewLifecycleOwner, EventObserver { searchResult ->
                submitList(searchResult)
            })
        }
    }

    // -------------------- 지역 옵션 관리 --------------------

    private fun handleSelectedLocationOption() {
        binding.tlOptionLocation.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                searchViewModel.setLocation(tab?.contentDescription.toString())
                Timber.d("선택된 지역 옵션 : ${tab?.contentDescription}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }


    // -------------------- 카테고리 옵션 관리 --------------------

    private fun handleCategoryOptionEvent() {
        binding.cgSearchCategoryGroup.setOnCheckedStateChangeListener { group, _ ->
            val checkedChip = getCheckedChip(group.checkedChipId)
            initCategoryOption(checkedChip)
        }
    }

    private fun getCheckedChip(checkedId: Int): Chip {
        return requireActivity().findViewById(checkedId) as Chip
    }

    private fun initCategoryOption(checkedChip: Chip) {
        selectedCategoryOption = checkedChip.contentDescription.toString()
        searchViewModel.setCategoryName(selectedCategoryOption)
    }

    private fun initCheckedCategory() {
        for(i in 0 until binding.cgSearchCategoryGroup.childCount) {
            val childChip = binding.cgSearchCategoryGroup.getChildAt(i) as Chip
            if(searchViewModel.isSame(childChip.contentDescription.toString())) {
                childChip.isChecked = true
            }
        }
    }

    // -------------------- 사용자 입력 값 관리 --------------------

    private fun handleUserInput() {
        binding.etSearchBar.setOnEditorActionListener(object: OnEditorActionListener {
            override fun onEditorAction(view: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                    view?.text?.let{
                        //TODO(공백이나 빈 입력에 대한 스낵바 메세지 나중에 추가할 것)
                        searchViewModel.setUserInput(view.text.trim().toString())
                    }
                    return true
                }
                return false
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchViewModel.initOption()
    }

}