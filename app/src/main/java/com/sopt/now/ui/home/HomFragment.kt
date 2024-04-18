package com.sopt.now.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sopt.now.data.Profiles
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.ui.adapter.FriendAdapter
import com.sopt.now.ui.home.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "바인딩 객체를 생성하지 않음"
        }

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = viewModel.friendList
        val friendAdapter = FriendAdapter(items)
        binding.rvFriends.adapter = friendAdapter
        setFriendList(items)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setFriendList(friendList: List<Profiles>) {
        friendList.toList()
    }

}