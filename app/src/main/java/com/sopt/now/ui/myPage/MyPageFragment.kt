package com.sopt.now.ui.myPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sopt.now.databinding.FragmentMyPageBinding

class MyPageFragment(
    private val id: String,
    private val pw: String,
    private val nickname: String,
    private val mbti: String,
) : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "바인딩 객체 미생성"
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMainProfile(id, pw, nickname, mbti)

    }

    private fun setMainProfile(id: String, pw: String, nickname: String, mbti: String) {
        with(binding) {
            tvMyId.text = id
            tvMyPw.text = pw
            tvMyNickname.text = nickname
            tvMyPhoneNumber.text = mbti
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}