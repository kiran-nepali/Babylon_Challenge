package com.example.babylonchallenge.data.model.users

import com.google.gson.annotations.SerializedName


data class Users (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("username") val username : String,
	@SerializedName("email") val email : String,
	@SerializedName("address") val address : Address,
	@SerializedName("phone") val phone : String,
	@SerializedName("website") val website : String,
	@SerializedName("company") val company : Company
)