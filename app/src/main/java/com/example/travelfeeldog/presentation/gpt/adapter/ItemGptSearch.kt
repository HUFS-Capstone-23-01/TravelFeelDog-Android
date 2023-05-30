package com.example.travelfeeldog.presentation.gpt.adapter

data class ItemGptSearch(
    val id: Int,
    val userInput: String,
    var gptResponse: String
)
