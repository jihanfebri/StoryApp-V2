package com.example.story.view.data.model

import androidx.lifecycle.ViewModel
import com.example.story.livedata.preference.repo.DataStoryRepo

class MapsModel(private val dataStory: DataStoryRepo) : ViewModel() {
    fun getLocation() = dataStory.getStoriesWithLocation()
}