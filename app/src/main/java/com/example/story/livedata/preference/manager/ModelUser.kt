package com.example.story.livedata.preference.manager

data class ModelUser(
    val name: String,
    val userId: String,
    val token: String,
    val isLogin: Boolean = false
)
