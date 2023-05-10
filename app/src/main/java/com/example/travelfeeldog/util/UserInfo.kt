package com.example.travelfeeldog.util

import com.example.travelfeeldog.data.model.common.MemberInfoBody
import com.google.gson.annotations.SerializedName

object UserInfo {
    private var memberInfo: MemberInfoBody? = null

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
}