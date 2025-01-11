package com.example.story.livedata.preference.repo

sealed class Results<out R> private constructor() {
    data class Success<out T>(val result: T) : Results<T>()
    data class Error(val error: String) : Results<Nothing>()
    object Loading : Results<Nothing>()
}
