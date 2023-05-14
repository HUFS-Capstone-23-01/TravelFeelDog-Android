package com.example.travelfeeldog.presentation.search

import android.os.Bundle
import android.view.View
import androidx.core.view.get
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentSearchBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import timber.log.Timber

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedTab = binding.tlOptionLocation[binding.tlOptionLocation.selectedTabPosition]
        Timber.d("선택된 지역 옵션 : ${binding.tlOptionLocation.getTabAt(binding.tlOptionLocation.selectedTabPosition)?.text.toString()}")
        Timber.d("선택된 지역 옵션 : ${binding.tlOptionLocation.selectedTabPosition}")

        handleSelectedLocationOption()
    }
    private fun handleSelectedLocationOption() {
        binding.tlOptionLocation.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Timber.d("선택된 지역 옵션 : ${tab?.text}")
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

}