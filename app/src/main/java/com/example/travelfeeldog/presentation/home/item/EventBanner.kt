package com.example.travelfeeldog.presentation.home.item

import com.example.travelfeeldog.R

data class EventBannerBody(
    val background: Int,
    val image: Int,
    val subTitle: Int,
    val mainTitle: Int
)
class EventBanner() {
    private val eventBannerList: MutableList<EventBannerBody> = mutableListOf(
        EventBannerBody(
            R.drawable.bg_gradient_red_pinkpurple,
            R.drawable.img_3d_package,
            R.string.first_sub_title,
            R.string.first_main_title
        ),
        EventBannerBody(
            R.drawable.bg_gradient_blue_purple,
            R.drawable.ic_3d_discount,
            R.string.second_sub_title,
            R.string.second_main_title
        )
    )
    fun getBannerList(): MutableList<EventBannerBody> {
        return eventBannerList
    }
}