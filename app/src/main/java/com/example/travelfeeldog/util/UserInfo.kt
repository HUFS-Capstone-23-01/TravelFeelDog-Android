package com.example.travelfeeldog.util

import com.example.travelfeeldog.data.model.common.MemberInfoBody
import com.example.travelfeeldog.presentation.gpt.adapter.ItemGptSearch
import com.google.gson.annotations.SerializedName

object UserInfo {
    private var memberInfo: MemberInfoBody? = null
    private val userGptHistory: MutableList<ItemGptSearch> = mutableListOf()

    fun setUserInfo(memberInfo: MemberInfoBody) {
        if(this.memberInfo == null) {
            this.memberInfo = memberInfo
        }
    }

    fun getUserInfo(): MemberInfoBody? {
        if(this.memberInfo != null) {
            return memberInfo!!
        }
        return null
    }

    fun addGptHistory(info: ItemGptSearch) {
        userGptHistory.add(info)
    }

    fun getGptHistoryList(): List<ItemGptSearch> {
        return userGptHistory.toList()
    }

    fun updateResponseHistory(index: Int, response: String) {
        userGptHistory[index].gptResponse = response
    }
}