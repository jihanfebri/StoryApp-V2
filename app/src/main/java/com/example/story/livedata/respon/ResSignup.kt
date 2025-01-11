package com.example.story.livedata.respon

import com.google.gson.annotations.SerializedName

data class ResSignup(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
