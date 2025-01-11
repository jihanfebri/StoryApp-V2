package com.example.story.view.data.model

import androidx.lifecycle.ViewModel
import com.example.story.livedata.preference.repo.DataStoryRepo
import java.io.File

class StoryModel(private val dataStory: DataStoryRepo) : ViewModel() {

    fun uploadImage(file: File, description: String) = dataStory.uploadImage(file, description)
}