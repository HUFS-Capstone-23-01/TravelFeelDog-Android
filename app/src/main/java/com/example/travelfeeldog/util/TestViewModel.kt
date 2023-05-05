package com.example.travelfeeldog.util

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelfeeldog.data.model.test.TestResponse
import com.example.travelfeeldog.data.network.api.TestApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestViewModel(private val testApi: TestApi) : ViewModel() {

    fun testConnectServer() {
        viewModelScope.launch {
            testApi.testConnectServer(1).enqueue(object : Callback<TestResponse> {

                    override fun onResponse(call: Call<TestResponse>, response: Response<TestResponse>) {
                        response.body().let {
                            Log.d("CONNECT SUCCESS!", it?.header.toString())
                        }
                    }
                    override fun onFailure(call: Call<TestResponse>, t: Throwable) {
                        Log.d("CONNECT FAIL!", t.toString())
                    }
            })
        }
    }

}