package com.example.story.livedata.preference.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.story.livedata.preference.manager.ModelUser
import com.example.story.livedata.preference.manager.Preference
import com.example.story.livedata.remote.ApiService
import com.example.story.livedata.respon.HomeResponse
import com.example.story.livedata.respon.ListStoryItem
import com.example.story.livedata.respon.ResStory
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import timber.log.Timber
import java.io.File

class DataStoryRepo private constructor(
    private val apiService: ApiService,
    private val userPreference: Preference
) {

    fun getSession(): Flow<ModelUser> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    fun getStoriesWithLocation(): LiveData<Results<HomeResponse>> = liveData {
        emit(Results.Loading)
        try {
            val response = apiService.getStoriesWithLocation(1)
            emit(Results.Success(response))
        } catch (e: Exception) {
            Timber.tag("ListStoryViewModel")
                .d("getStoriesWithLocation: " + e.message.toString() + " ")
            emit(Results.Error(e.message.toString()))
        }
    }

    fun uploadImage(imageFile: File, description: String) = liveData {
        emit(Results.Loading)
        val requestBody = description.toRequestBody("text/plain".toMediaType())
        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "photo",
            imageFile.name,
            requestImageFile
        )
        try {
            val successResponse = apiService.uploadStory(multipartBody, requestBody)
            emit(Results.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, ResStory::class.java)
            emit(Results.Error(errorResponse.message))
        }

    }

    fun getStory() : LiveData<PagingData<ListStoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                PagingStory(apiService)
            }
        ).liveData
    }

    companion object {
        @Volatile
        var instance: DataStoryRepo? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: Preference
        ): DataStoryRepo =
            instance ?: synchronized(this) {
                instance ?: DataStoryRepo(apiService, userPreference)
            }.also { instance = it }
    }
}
