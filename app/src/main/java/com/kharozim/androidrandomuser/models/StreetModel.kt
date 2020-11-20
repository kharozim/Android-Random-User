package com.kharozim.androidrandomuser.models
import com.google.gson.annotations.SerializedName

data class StreetModel(
    @field:SerializedName("number")
    val number: Int,

    @field:SerializedName("name")
    val name: String
)
