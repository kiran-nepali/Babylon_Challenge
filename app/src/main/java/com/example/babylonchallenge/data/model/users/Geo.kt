package com.example.babylonchallenge.data.model.users

import com.google.gson.annotations.SerializedName

data class Geo (

	@SerializedName("lat") val lat : Double,
	@SerializedName("lng") val lng : Double
)