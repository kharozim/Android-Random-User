package com.kharozim.androidrandomuser.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kharozim.androidrandomuser.databinding.ItemUserBinding
import com.kharozim.androidrandomuser.databinding.ItemUserCategoryBinding
import com.kharozim.androidrandomuser.models.UserModel
import java.util.*


sealed class User {
    data class Header(val category : String) : User()
    data class Row(val item: UserModel) : User()
}

class UserAdapter(
   private val context: Context,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var lists = mutableListOf<User>()
    fun setData(data: MutableList<User>) {
        this.lists = data
        notifyDataSetChanged()
    }


    private val header = 0
    private val row = 1

    inner class RowViewHolder(
         val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(userModel: UserModel) {
            binding.run {
                tvName.text = userModel.name.title+" "+userModel.name.first+" "+userModel.name.last
                tvEmail.text = userModel.email
                tvPhone.text = userModel.phone
                Glide.with(root).load(userModel.picture.medium).circleCrop().into(ivImage)
            }

        }

    }

    inner class HeaderViewHolder(
         val binding: ItemUserCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(category: String) {
            binding.tvCategory.text = category.toUpperCase(Locale.getDefault())
        }
    }

    override fun getItemViewType(position: Int): Int = when (lists[position]) {

            is User.Header -> header
            is User.Row -> row

        else -> throw IllegalArgumentException("Unsupported view type")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            header -> HeaderViewHolder(
                ItemUserCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
            )
            row -> RowViewHolder(
                ItemUserBinding.inflate(LayoutInflater.from(context), parent, false)
            )
            else -> throw IllegalArgumentException("Unsupported view type")
        }
    }

    override fun getItemCount(): Int = lists.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val user = lists[position]
        if ( user is User.Row && holder is RowViewHolder){
            holder.bindData(user.item)    // use view binding
        } else if (user is User.Header && holder is HeaderViewHolder){
            holder.bindData(user.category) // use view binding
        }
    }
}