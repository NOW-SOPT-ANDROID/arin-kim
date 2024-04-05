package com.sopt.now.compose.ui.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.ui.home.HomeScreen
import com.sopt.now.compose.ui.login.LoginScreen
import com.sopt.now.compose.ui.signUp.SignUpScreen

@Composable
fun NowSoptAppNavHost() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onNavigateToHome = { navController.navigate("home") },
                onNavigateToSignUp = { navController.navigate("signup") }
            )
        }
        composable("home") { HomeScreen() }
        composable("signup") {
            SignUpScreen(onNavigateToLogin = {
                navController.navigate("login") {
                    // 지금 까지의 백스택 삭제
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            })
        }
    }
}