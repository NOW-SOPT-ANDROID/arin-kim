package com.sopt.now.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sopt.now.R
import com.sopt.now.databinding.ActivityHomeBinding
import com.sopt.now.ui.home.HomeFragment
import com.sopt.now.ui.myPage.MyPageFragment
import com.sopt.now.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var nickname: String
    private lateinit var mbti: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFragment()
        clickBottomNavigation()
    }

    override fun onResume() {
        super.onResume()

        initIntent()
    }

    private fun initIntent() {
        intent.run {
            id = getStringExtra(ID).toString()
            pw = getStringExtra(PW).toString()
            nickname = getStringExtra(NICKNAME).toString()
            mbti = getStringExtra(MBTI).toString()
        }
    }

    private fun initFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(binding.fcvHome.id)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.fcvHome.id, HomeFragment())
                .commit()
        }
    }

    private fun clickBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(
                        HomeFragment()
                    )
                    true
                }

                R.id.menu_search -> {
                    replaceFragment(SearchFragment())
                    true
                }

                R.id.menu_my_page -> {
                    replaceFragment(
                        MyPageFragment(
                            id, pw, nickname, mbti
                        )
                    )
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fcvHome.id, fragment)
            .commit()
    }

    companion object {
        const val ID = "id"
        const val PW = "pw"
        const val NICKNAME = "nickname"
        const val MBTI = "mbti"
    }
}