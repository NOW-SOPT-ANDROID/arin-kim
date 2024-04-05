package com.sopt.now

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var nickname: String
    private lateinit var mbti: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getStringExtra("id").toString()
        pw = intent.getStringExtra("pw").toString()
        nickname = intent.getStringExtra("nickname").toString()
        mbti = intent.getStringExtra("mbti").toString()

        with(binding) {
            tvMyId.text = id
            tvMyPw.text = pw
            tvMyNickname.text = nickname
            tvMyMbti.text = mbti
        }
    }
}