package com.example.story.view.data.model

import androidx.lifecycle.ViewModel
import com.example.story.livedata.respon.ResSignup
import com.example.story.livedata.preference.LiveUser


class RegisterModel(private val liveUser: LiveUser) : ViewModel(){
    suspend fun register (name: String, email: String, password: String) : ResSignup {
        return liveUser.registerUser(name, email, password)
    }
}