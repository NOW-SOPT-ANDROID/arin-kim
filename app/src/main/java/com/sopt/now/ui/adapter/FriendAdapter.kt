package com.sopt.now.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.Friend
import com.sopt.now.databinding.ItemFriendBinding

class FriendAdapter : RecyclerView.Adapter<FriendViewHolder>() {
    // 임시의 빈 리스트
    private var friendList: List<Friend> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFriendBinding.inflate(inflater, parent, false)
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.onBind(friendList[position])
    }

    override fun getItemCount() = friendList.size

    fun setFriendList(friendList: List<Friend>) {
        this.friendList = friendList.toList()
    }
}

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: Friend) {
        binding.run {
            ivProfile.setImageResource(friendData.profileImage)
            tvName.text = friendData.name
            tvDescription.text = friendData.description
        }
    }
}