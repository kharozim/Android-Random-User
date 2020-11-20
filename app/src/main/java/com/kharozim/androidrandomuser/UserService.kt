package com.kharozim.androidrandomuser

import com.kharozim.androidrandomuser.models.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("api")
    fun getAllUser(
        @Query("results")
        results: Int = 100
    ): Call<UserResponse>

}
