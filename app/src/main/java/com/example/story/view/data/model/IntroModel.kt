package com.example.story.view.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.story.livedata.preference.LiveUser
import com.example.story.livedata.preference.manager.ModelUser

class IntroModel(private val repository: LiveUser) : ViewModel() {
    fun getSession(): LiveData<ModelUser> {
        return repository.getSession().asLiveData()
    }
}
