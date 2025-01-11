package com.example.story.livedata.respon

import com.google.gson.annotations.SerializedName

data class ResStory(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)
