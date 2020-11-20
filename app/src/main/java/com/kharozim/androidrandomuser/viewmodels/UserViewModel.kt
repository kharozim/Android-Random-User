package com.kharozim.androidrandomuser.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kharozim.androidrandomuser.ApiClient
import com.kharozim.androidrandomuser.UserResponse
import com.kharozim.androidrandomuser.models.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(val context: Context?) : ViewModel() {
    val _users: MutableLiveData<List<UserModel>> by lazy { MutableLiveData<List<UserModel>>() }

    val users : LiveData<List<UserModel>>
    get() = _users


    fun setAlluser(){
        ApiClient.userService.getAllUser().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    _users.postValue(response.body()?.results)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}