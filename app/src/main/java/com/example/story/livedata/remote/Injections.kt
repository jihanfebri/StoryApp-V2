package com.example.story.livedata.remote

import android.content.Context
import com.example.story.livedata.preference.LiveUser
import com.example.story.livedata.preference.manager.Preference
import com.example.story.livedata.preference.manager.dataStore
import com.example.story.livedata.preference.repo.DataStoryRepo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injections {
    fun provideRepository(context: Context): LiveUser {
        val pref = Preference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiClient.getApiService(user.token)
        return LiveUser.getInstance(apiService, pref)
    }

    fun provideStoryRepository(context: Context): DataStoryRepo {
        val pref = Preference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiClient.getApiService(user.token)
        return DataStoryRepo.getInstance(apiService, pref)
    }
}