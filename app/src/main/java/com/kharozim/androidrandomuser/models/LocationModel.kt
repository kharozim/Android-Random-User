package com.kharozim.androidrandomuser.models
import com.google.gson.annotations.SerializedName

data class LocationModel (
    @field:SerializedName("country")
    val country: String,

    @field:SerializedName("city")
    val city: String,

    @field:SerializedName("street")
    val street: StreetModel,

    @field:SerializedName("postcode")
    val postcode: Any,

    @field:SerializedName("state")
    val state: String
)
