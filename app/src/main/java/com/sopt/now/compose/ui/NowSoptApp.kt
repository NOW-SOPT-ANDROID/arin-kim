package com.sopt.now.compose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.ui.home.HomeScreen
import com.sopt.now.compose.ui.login.LoginScreen
import com.sopt.now.compose.ui.signUp.SignUpScreen

@Composable
fun NowSoptApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen() }
        composable("home") { HomeScreen() }
        composable("signup") { SignUpScreen() }
    }
}