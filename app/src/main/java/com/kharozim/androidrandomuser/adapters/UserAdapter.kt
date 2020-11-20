package com.kharozim.androidrandomuser.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kharozim.androidrandomuser.R
import com.kharozim.androidrandomuser.databinding.ItemUserBinding
import com.kharozim.androidrandomuser.models.UserModel

class UserAdapter(
    val context: Context,
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var userList = listOf<UserModel>()
    fun setData(data: List<UserModel>) {
        this.userList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemUserBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_user, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userBinding.user = userList[position]
    }

    override fun getItemCount(): Int = userList.size

    class ViewHolder(
        val userBinding: ItemUserBinding
    ) : RecyclerView.ViewHolder(userBinding.root) {
        var binding: ItemUserBinding? = null

        init {
            this.binding = userBinding
        }

        companion object {

            @JvmStatic
            @BindingAdapter("loadImage")
            fun loadImage(view: ImageView, url: String) {
                Glide.with(view.context).load(url).circleCrop().into(view)
            }
        }
    }
}