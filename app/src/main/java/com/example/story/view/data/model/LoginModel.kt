package com.example.story.view.data.model

import androidx.lifecycle.ViewModel
import com.example.story.livedata.preference.manager.ModelUser
import com.example.story.livedata.preference.LiveUser

class LoginModel(private val repository: LiveUser) : ViewModel() {
    fun saveSession(user: ModelUser) {
        repository.saveSession(user)
    }

    fun login(email: String, password: String) = repository.loginUser(email, password)
}