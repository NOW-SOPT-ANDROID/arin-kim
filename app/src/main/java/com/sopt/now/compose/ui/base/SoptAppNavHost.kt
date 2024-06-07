package com.sopt.now.compose.ui.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.ui.MainScreen
import com.sopt.now.compose.ui.signIn.SignInScreen
import com.sopt.now.compose.ui.signUp.SignUpScreen

@Composable
fun SoptAppNavHost() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "sign_in") {
        composable("sign_in") {
            SignInScreen(
                onNavigateToHome = navController,
                onNavigateToSignUp = { navController.navigate("signup") },
            )
        }

        composable(
            "main",
        ) {
            MainScreen()
        }
        composable("signup") {
            SignUpScreen(onNavigateToSignIn = navController)
        }
    }
}