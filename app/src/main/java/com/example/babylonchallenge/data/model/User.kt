package com.example.babylonchallenge.data.model.users

import com.google.gson.annotations.SerializedName


data class User (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("username") val username : String
)