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
import com.kharozim.androidrandomuser.databinding.ItemUserCategoryBinding
import com.kharozim.androidrandomuser.models.UserModel
import java.util.*


sealed class User {
    data class Header(val category : String) : User()
    data class Row(val item: UserModel) : User()
}


class UserAdapter(
    val context: Context,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var userList = listOf<User>()
    fun setData(data: List<User>) {
        this.userList = data
        notifyDataSetChanged()
    }

    private val header = 0
    private val row = 1

    inner class RowViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(userModel: UserModel) {
            // TODO: 11/20/20
        }
    }

    inner class HeaderViewHolder(
        private val binding: ItemUserCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(category: String) {
            binding.tvCategory.text = category[0].toUpperCase().toString()
        }
    }

    override fun getItemViewType(position: Int): Int = when (userList[position]) {

            is User.Header -> header
            is User.Row -> row

        else -> throw IllegalArgumentException("Unsupported view type")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val inflater = LayoutInflater.from(context)
//        val binding: ItemUserBinding =
//            DataBindingUtil.inflate(inflater, R.layout.item_user, parent, false)

//        return ViewHolder(binding)

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

//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.userBinding.user = userList[position]
//    }

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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}