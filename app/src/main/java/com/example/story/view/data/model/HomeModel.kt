package com.example.story.view.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.story.livedata.preference.manager.ModelUser
import com.example.story.livedata.preference.repo.DataStoryRepo
import com.example.story.livedata.respon.ListStoryItem
import kotlinx.coroutines.launch

class HomeModel(private val dataStory: DataStoryRepo) : ViewModel() {

    val getStory: LiveData<PagingData<ListStoryItem>> =
        dataStory.getStory().cachedIn(viewModelScope)

    fun getSession(): LiveData<ModelUser> {
        return dataStory.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            dataStory.logout()
        }

    }
}
