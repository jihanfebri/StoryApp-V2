package com.example.story.view

import com.example.story.view.data.model.IntroModel
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.story.livedata.preference.repo.DataStoryRepo
import com.example.story.livedata.remote.Injections
import com.example.story.livedata.preference.LiveUser
import com.example.story.view.data.model.HomeModel
import com.example.story.view.data.model.LoginModel
import com.example.story.view.data.model.MapsModel
import com.example.story.view.data.model.RegisterModel
import com.example.story.view.data.model.StoryModel

class FactoryView(private val liveUser: LiveUser, private val dataStory: DataStoryRepo) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(IntroModel::class.java) -> {
                IntroModel(liveUser) as T
            }
            modelClass.isAssignableFrom(LoginModel::class.java) -> {
                LoginModel(liveUser) as T
            }
            modelClass.isAssignableFrom(HomeModel::class.java) -> {
                HomeModel(dataStory) as T
            }
            modelClass.isAssignableFrom(RegisterModel::class.java) -> {
                RegisterModel(liveUser) as T
            }
            modelClass.isAssignableFrom(StoryModel::class.java) -> {
                StoryModel(dataStory) as T
            }
            modelClass.isAssignableFrom(MapsModel::class.java) -> {
                MapsModel(dataStory) as T
            }
            // Jika modelClass bukan merupakan subclass dari kelas yang dikenal, lemparkan IllegalArgumentException
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: FactoryView? = null
        @JvmStatic
        fun getInstance(context: Context): FactoryView {
            if (INSTANCE == null) {
                synchronized(FactoryView::class.java) {
                    INSTANCE = FactoryView(Injections.provideRepository(context), Injections.provideStoryRepository(context))
                }
            }
            return INSTANCE as FactoryView
        }
        fun clearInstance() {
            INSTANCE = null
            LiveUser.clearInstance()
        }
    }
}
