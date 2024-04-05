package com.sopt.now

import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private lateinit var id: String
//    private lateinit var pw: String
//    private lateinit var nickname: String
//    private lateinit var mbti: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == RESULT_OK) {
//                id = result.data?.getStringExtra("id") ?: ""
//                pw = result.data?.getStringExtra("pw") ?: ""
//                nickname = result.data?.getStringExtra("nickname") ?: ""
//                mbti = result.data?.getStringExtra("mbti") ?: ""
//            }
//        }
//        Log.d("Nickname", nickname)

//        binding.tvMyId.text = id
//        binding.tvMyPw.text = pw
//        binding.tvMyNickname.text = nickname
//        binding.tvMyMbti.text = mbti

    }
}