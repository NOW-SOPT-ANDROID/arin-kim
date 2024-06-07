package com.sopt.now.ui.myPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sopt.now.databinding.FragmentMyPageBinding
import com.sopt.now.ui.myPage.viewModel.MyPageViewModel
import kotlinx.coroutines.launch

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "바인딩 객체 미생성"
        }

    private val viewModel by viewModels<MyPageViewModel>()

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

        setCollect()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setCollect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.infoState.collect { info ->
                    with(binding) {
                        tvMyId.text = info.data.authenticationId
                        tvMyNickname.text = info.data.nickname
                        tvMyPhoneNumber.text = info.data.phone
                    }
                }
            }
        }
    }
}