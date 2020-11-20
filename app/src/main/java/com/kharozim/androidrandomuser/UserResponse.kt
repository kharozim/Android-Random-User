package com.kharozim.androidrandomuser

import com.google.gson.annotations.SerializedName
import com.kharozim.androidrandomuser.models.UserModel

data class UserResponse(
    @field:SerializedName("results")
    val results : List<UserModel>
)