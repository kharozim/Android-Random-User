package com.kharozim.androidrandomuser.models

import android.graphics.Picture
import android.location.Location
import com.google.gson.annotations.SerializedName
import java.util.jar.Attributes

data class UserModel(

	@field:SerializedName("id")
	val id: IdModel,
	@field:SerializedName("name")
	val name: NameModel,
	@field:SerializedName("email")
	val email: String,
	@field:SerializedName("location")
	val location: LocationModel,
	@field:SerializedName("dob")
	val dob: DobModel,
	@field:SerializedName("phone")
	val phone: String,
	@field:SerializedName("cell")
	val cell: String,
	@field:SerializedName("picture")
	val picture: PictureModel,
	@field:SerializedName("gender")
	val gender: String,
	@field:SerializedName("nat")
	val nat: String
)