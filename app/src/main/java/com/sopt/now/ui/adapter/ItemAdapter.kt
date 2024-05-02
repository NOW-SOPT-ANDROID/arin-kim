package com.sopt.now.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sopt.now.data.MultiData
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.databinding.ItemMyProfileBinding

class ItemAdapter(private val items: MutableList<MultiData>) : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_MY_PROFILE = 0
        private const val VIEW_TYPE_FRIEND = 1
    }

    interface ItemClick {
        fun onItemClick(view: View, position: Int)
    }

    private var itemClick: ItemClick? = null


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
            is MultiData.MyProfile -> {
                (holder as MyProfileViewHolder).profileImage.setImageResource(item.profileImage!!)
                holder.name.text = item.name
                holder.description.text = item.description
            }

            is MultiData.Friend -> {
                (holder as FriendViewHolder).profileImage.setImageResource(item.profileImage!!)
                holder.name.text = item.name
                holder.description.text = item.description
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
            is MultiData.MyProfile -> VIEW_TYPE_MY_PROFILE
            is MultiData.Friend -> VIEW_TYPE_FRIEND
        }
    }

    private class MyProfileViewHolder(binding: ItemMyProfileBinding) :
        ViewHolder(binding.root) {

        val profileImage = binding.ivProfile
        val name = binding.tvName
        val description = binding.tvDescription
    }

    private class FriendViewHolder(binding: ItemFriendBinding) :
        ViewHolder(binding.root) {

        val profileImage = binding.ivProfile
        val name = binding.tvName
        val description = binding.tvDescription

    }
}

