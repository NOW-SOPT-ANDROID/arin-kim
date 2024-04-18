package com.sopt.now.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sopt.now.data.Profiles
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.databinding.ItemMyProfileBinding

class FriendAdapter(val items: MutableList<Profiles>) : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_MY_PROFILE = 0
        private const val VIEW_TYPE_FRIEND = 1
    }

    interface ItemClick {
        fun onItemClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_MY_PROFILE -> {
                val binding = ItemMyProfileBinding.inflate(inflater, parent, false)
                MyProfileViewHolder(binding)
            }

            else -> {
                val binding = ItemFriendBinding.inflate(inflater, parent, false)
                FriendViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item = items[position]) {
            is Profiles.MyProfile -> {
                (holder as MyProfileViewHolder).name.text = item.name
            }

            is Profiles.Friend -> {
                (holder as FriendViewHolder).name.text = item.name

                holder.itemView.setOnClickListener {
                    itemClick?.onItemClick(it, position)
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Profiles.MyProfile -> VIEW_TYPE_MY_PROFILE
            is Profiles.Friend -> VIEW_TYPE_FRIEND
        }
    }

    inner class MyProfileViewHolder(private val binding: ItemMyProfileBinding) :
        ViewHolder(binding.root) {

        val profileImage = binding.ivProfile
        val name = binding.tvName
        val description = binding.tvDescription
    }

    inner class FriendViewHolder(binding: ItemFriendBinding) :
        ViewHolder(binding.root) {

        val profileImage = binding.ivProfile
        val name = binding.tvName
        val description = binding.tvDescription

    }
}

