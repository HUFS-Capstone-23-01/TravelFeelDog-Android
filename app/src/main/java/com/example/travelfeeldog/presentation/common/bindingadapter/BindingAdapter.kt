package com.example.travelfeeldog.presentation.common.bindingadapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.travelfeeldog.R
import com.example.travelfeeldog.util.Event

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("setVisibility")
    fun setVisibility(view: View, value:  Boolean) {
        if (value) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.INVISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("setImageUrl")
    fun setImage(view: ImageView, url: String) {
        Glide.with(view)
            .load(url)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("setDate")
    fun setDate(view: TextView, rawDate: String) {
        view.text = rawDate.substring(0,10)
    }

    @JvmStatic
    @BindingAdapter("setManyImageUrl", "order")
    fun setManyImageUrl(view: ImageView, urls: List<String>, order: Int) {
        if(order < urls.size) {
            setImage(view, urls[order])
        } else {
            view.visibility = View.GONE
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
        }
    }


}