package com.example.story.livedata.preference

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.story.livedata.preference.manager.ModelUser
import com.example.story.livedata.preference.manager.Preference
import com.example.story.livedata.preference.repo.DataStoryRepo
import com.example.story.livedata.preference.repo.Results
import com.example.story.livedata.remote.ApiService
import com.example.story.livedata.respon.ResponseLogin
import com.example.story.livedata.respon.ResSignup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LiveUser private constructor(
    private val apiService: ApiService,
    private val userPreference: Preference
) : CoroutineScope {

    fun saveSession(user: ModelUser) {
        launch(Dispatchers.IO) {
            userPreference.saveSession(user)
        }
    }

    fun getSession(): Flow<ModelUser> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    suspend fun registerUser(name: String, email: String, password: String) : ResSignup {
        return apiService.register(name, email, password)
    }

    fun loginUser(email: String, password: String) : LiveData<Results<ResponseLogin>> = liveData {
        emit(Results.Loading)
        try {
            val result = apiService.login(email, password)
            if (result.error == false) {
                emit(Results.Success(result))
            } else {
                emit(Results.Error(result.message.toString()))
            }
        } catch (e: Exception) {
            emit(Results.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: LiveUser? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: Preference
        ): LiveUser =
            instance ?: synchronized(this) {
                instance ?: LiveUser(apiService, userPreference)
            }.also { instance = it }

        fun clearInstance() {
            DataStoryRepo.instance = null
        }
    }

    override val coroutineContext: CoroutineContext get() = Dispatchers.Main
}
