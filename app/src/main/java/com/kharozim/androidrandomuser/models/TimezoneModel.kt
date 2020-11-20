package com.kharozim.androidrandomuser.models

import com.google.gson.annotations.SerializedName

data class TimezoneModel(

    @field:SerializedName("offset")
    val offset: String,

    @field:SerializedName("description")
    val description: String
)