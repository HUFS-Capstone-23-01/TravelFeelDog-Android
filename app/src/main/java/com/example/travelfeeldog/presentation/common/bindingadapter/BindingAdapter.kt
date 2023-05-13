package com.example.travelfeeldog.presentation.common.bindingadapter

import android.text.TextUtils.concat
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.travelfeeldog.R
import com.example.travelfeeldog.data.model.place.ReviewInfo
import com.example.travelfeeldog.util.Event
import timber.log.Timber

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("convertTextStringToInt")
    fun convertStringToIntFromTextView(view: TextView, value: Int?) {
        value?.let {
            view.text = value.toString()
        }
    }

    @JvmStatic
    @BindingAdapter("setVisibility")
    fun setVisibility(view: View, value:  Boolean) {
        if (value) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("setImageUrl")
    fun setImage(view: ImageView, url: String?) {
        url?.let{
            Glide.with(view)
                .load(url)
                .into(view)
            view.visibility = View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("setDate")
    fun setDate(view: TextView, rawDate: String) {
        view.text = rawDate.substring(0,10)
    }

    @JvmStatic
    @BindingAdapter("setManyImageUrl", "order")
    fun setManyImageUrl(view: ImageView, urls: List<String>?, order: Int) {
        urls?.let{
            if(order < urls.size) {
                setImage(view, urls[order])
            } else {
                view.visibility = View.GONE
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setEnabled")
    fun setEnabled(view: View, value: Boolean?) {
        view.isEnabled = value == true
    }

    @JvmStatic
    @BindingAdapter("setStrokeTextViewStateUi")
    fun setStrokeTextViewStateUi(view: TextView, value: Boolean) {
        if(value) {
            with(view) {
                setBackgroundResource(R.drawable.bg_box_green)
                setTextColor(resources.getColor(R.color.primary_color, null))
            }
        } else {
            with(view) {
                setBackgroundResource(R.drawable.bg_box_gray)
                setTextColor(resources.getColor(R.color.gray1, null))
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setSolidButtonStateUi")
    fun setSolidButtonStateUi(view: Button, value: Boolean) {
        if(value) {
            view.setBackgroundResource(R.drawable.bg_request_btn_green)
        } else{
            view.setBackgroundResource(R.drawable.bg_request_btn_gray)
        }
    }

    @JvmStatic
    @BindingAdapter("setEmotionTextView")
    fun setEmotionTextView(view: TextView, value: String) {
        if(view.contentDescription == value) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.INVISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("combineTextBehind")
    fun combineTextBehind(view: TextView, value: Int) {
        view.text = concat(view.text.toString(), value.toString())
    }

    @JvmStatic
    @BindingAdapter("combineTextFront")
    fun combineTextFront(view: TextView, value: Int) {
        view.text = concat(value.toString(), view.text.toString())
    }

    // 유저 정보 관련

    @JvmStatic
    @BindingAdapter("setTextUserLevel")
    fun setTextUserLevel(view: TextView, value: Int) {
        view.text = concat("Lv ", value.toString())
    }

    @JvmStatic
    @BindingAdapter("setTextUserExperience")
    fun setTextUserExperience(view: TextView, value: Int) {
        view.text = concat(value.toString(), " / 100")
    }

    // 장소 페이지 관련

    @JvmStatic
    @BindingAdapter("setPlaceCategory")
    fun setPlaceCategory(view: TextView, categoryList: List<String>?) {
        categoryList?.let {
            for(categoryName in categoryList) {
                if(view.text.toString() == categoryName) {
                    view.visibility = View.VISIBLE
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setManyReviewText", "reviewOrder")
    fun setManyText(view: TextView, reviewList: List<ReviewInfo>?, order: Int) {
        reviewList?.let {
            if(order < reviewList.size) {
                view.text = reviewList[order].additionalScript
            } else {
                view.visibility = View.GONE
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setManyNicknameText", "nicknameOrder")
    fun setManyNicknameText(view: TextView, reviewList: List<ReviewInfo>?, order: Int) {
        reviewList?.let {
            if(order < reviewList.size) {
                view.text = reviewList[order].nickName
            } else {
                view.visibility = View.GONE
            }
        }
    }



}