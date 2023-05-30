package com.example.travelfeeldog.presentation.gpt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentGptSearchBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.CustomSnackBar
import com.example.travelfeeldog.presentation.common.LoadingUtil
import com.example.travelfeeldog.presentation.common.WindowUtil
import com.example.travelfeeldog.presentation.gpt.adapter.GptResponseAdapter
import com.example.travelfeeldog.presentation.gpt.adapter.ItemGptSearch
import com.example.travelfeeldog.presentation.gpt.viewmodel.GptViewModel
import com.example.travelfeeldog.util.EventObserver
import com.example.travelfeeldog.util.UserInfo
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GptSearchFragment : BaseFragment<FragmentGptSearchBinding>(R.layout.fragment_gpt_search) {

    private val gptViewModel: GptViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        WindowUtil.setWindow(requireActivity(), R.color.test_color, R.color.white)
        handleRequestSearchResult()

        binding.rvTextArea.adapter = GptResponseAdapter().apply {
            submitList(UserInfo.getGptHistoryList())

            gptViewModel.isRequested.observe(viewLifecycleOwner, EventObserver { isRequested ->
                UserInfo.addGptHistory(ItemGptSearch(UserInfo.getGptHistoryList().size, binding.etUserInput.text.toString(), ""))
                submitList(UserInfo.getGptHistoryList())
                binding.etUserInput.setText("")
            })
        }

        gptViewModel.gptHistory.observe(viewLifecycleOwner, EventObserver { history ->
            val index = binding.rvTextArea.childCount - 1
            LoadingUtil.cancelTaskProgressAnimation(binding.rvTextArea.getChildAt(index).findViewById(R.id.lav_response_loading))
            binding.rvTextArea.getChildAt(index)
                .findViewById<TextView>(R.id.tv_response)
                .text = history[history.size - 1].gptResponse
        })
    }

    private fun handleRequestSearchResult() {
        binding.ibRequest.setOnClickListener {
            if (!binding.etUserInput.text.isNullOrEmpty()) {
                gptViewModel.getGptSearchResult(binding.etUserInput.text.toString())
            } else {
                val drawable = ContextCompat.getDrawable(requireActivity(), R.drawable.ic_caution)!!
                CustomSnackBar.make(binding.root, "검색어를 입력해주세요", drawable).show()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()

        WindowUtil.setWindow(requireActivity(), R.color.white, R.color.white)
    }


}