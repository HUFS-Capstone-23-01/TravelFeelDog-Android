package com.example.travelfeeldog.presentation.review

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.get
import androidx.core.view.marginRight
import androidx.core.view.setPadding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.stream.UrlLoader
import com.bumptech.glide.util.ByteBufferUtil.toFile
import com.example.travelfeeldog.R
import com.example.travelfeeldog.data.model.review.BadKeyword
import com.example.travelfeeldog.data.model.review.GoodKeyword
import com.example.travelfeeldog.data.model.review.KeywordSet
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultRequest
import com.example.travelfeeldog.databinding.FragmentPlaceReviewBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.common.FileUtil
import com.example.travelfeeldog.presentation.common.LoadingUtil
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.navigateUp
import com.example.travelfeeldog.presentation.common.navigation.NavigationUtil.popBackStack
import com.example.travelfeeldog.presentation.place.viewmodel.PlaceViewModel
import com.example.travelfeeldog.presentation.review.viewmodel.PostReviewViewModel
import com.example.travelfeeldog.util.Event
import com.example.travelfeeldog.util.EventObserver
import com.example.travelfeeldog.util.UserInfo
import com.google.android.material.chip.Chip
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber
import java.io.File
import java.net.URL


class PlaceReviewFragment : BaseFragment<FragmentPlaceReviewBinding>(R.layout.fragment_place_review) {

    private val placeViewModel: PlaceViewModel by sharedViewModel()
    private val postReviewViewModel: PostReviewViewModel by sharedViewModel()
    private lateinit var positiveKeywordSet: List<GoodKeyword>
    private lateinit var negativeKeywordSet: List<BadKeyword>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postReviewViewModel.getPlaceKeyword(placeViewModel.placeCategoryId.value!!)

        postReviewViewModel.placeReviewKeywordSet.observe(viewLifecycleOwner, EventObserver { keywordSet ->
            positiveKeywordSet = keywordSet.goodKeyWords
            negativeKeywordSet = keywordSet.badKeyWords

            for (keyword in positiveKeywordSet) {
                val chip = makeChip(R.layout.item_custom_chip_positive, binding.cgLikeKeywordGroup, false)
                binding.cgLikeKeywordGroup.addView(chip.apply {
                    id = keyword.goodKeyWordId
                    text = keyword.goodKeyWordName
                    isCheckedIconVisible = false
                    isCheckable = true
                })
            }

            for (keyword in negativeKeywordSet) {
                val chip = makeChip(R.layout.item_custom_chip_negative, binding.cgDislikeKeywordGroup, false)
                binding.cgDislikeKeywordGroup.addView(chip.apply {
                    id = keyword.badKeyWordId
                    text = keyword.badKeyWordName
                    isCheckedIconVisible = false
                    isCheckable = true
                })
            }
        })

        postReviewViewModel.reviewImageSet.observe(viewLifecycleOwner, EventObserver { imageUrls ->
            val postingData = PlaceUserEvaluationResultRequest(
                placeViewModel.placeId.value!!,
                getSelectedEvaluation(binding.cgEvaluationGroup.checkedChipId),
                binding.npSmallDog.value,
                binding.npNormal.value,
                binding.npLarge.value,
                binding.cgLikeKeywordGroup.checkedChipIds,
                binding.cgDislikeKeywordGroup.checkedChipIds,
                binding.etvReviewInputBox.text.toString(),
                imageUrls
            )
            postReviewViewModel.postReview(UserInfo.getUserInfo()!!.token, postingData)
        })

        postReviewViewModel.isPostedReview.observe(viewLifecycleOwner, EventObserver { isPosted ->
            LoadingUtil.cancelTaskProgressAnimation(binding.lavLoading)
            if(isPosted) {
                Toast.makeText(requireActivity(), "리뷰가 등록됐습니다.", Toast.LENGTH_SHORT).show()
                navigateUp()
            } else {
                Toast.makeText(requireActivity(), "다시 시도해 주세요.", Toast.LENGTH_SHORT).show()
            }
        })

        binding.ibEvaluationClose.setOnClickListener {
            navigateUp()
        }

        binding.btnEvaluationConplete.setOnClickListener {
            postReviewViewModel.postReviewImage()
            LoadingUtil.showTaskProgressAnimation(binding.lavLoading)
        }

        setNumberPickerInit(binding.npSmallDog, 0, 50, 0)
        setNumberPickerInit(binding.npNormal, 0, 50, 0)
        setNumberPickerInit(binding.npLarge, 0, 50, 0)

        // Photo picker test
        val pickMultipleMedia =
            registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(3)) { uris ->
                if (uris.isNotEmpty()) {
                    binding.llPhotoContainer.removeAllViewsInLayout()
                    postReviewViewModel.initFileList()

                    for (uri in uris) {
                        val iv = layoutInflater.inflate(R.layout.item_photo_viewer, binding.llPhotoContainer, false) as ImageView
                        binding.llPhotoContainer.addView(iv.apply {
                            Glide
                                .with(this@PlaceReviewFragment)
                                .load(uri)
                                .into(this)
                        })

                        val file = FileUtil.toFile(requireActivity(), uri)
                        postReviewViewModel.convertFileToFormData(file)
                    }

                } else {
                    Timber.d("PhotoPicker : No media selected")
                }
            }

        binding.btnAddPhoto.setOnClickListener {
            pickMultipleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    private fun setNumberPickerInit(v: NumberPicker, min: Int, max: Int, initValue: Int) {
        v.minValue = min
        v.maxValue = max
        v.value = initValue
    }

    private fun getSelectedEvaluation(selectedId: Int): String {
        return requireActivity().findViewById<Chip>(selectedId).contentDescription.toString()
    }

    private fun makeChip(layout: Int, viewGroup: ViewGroup, attachToRoot: Boolean): Chip {
        return layoutInflater.inflate(layout, viewGroup, attachToRoot) as Chip
    }
}