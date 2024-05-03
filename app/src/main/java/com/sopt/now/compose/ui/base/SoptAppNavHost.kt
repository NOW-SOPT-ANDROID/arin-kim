package com.sopt.now.compose.ui.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sopt.now.compose.ui.MainScreen
import com.sopt.now.compose.ui.login.LoginScreen
import com.sopt.now.compose.ui.signUp.SignUpScreen

@Composable
fun SoptAppNavHost() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "sign_in") {
        composable("sign_in") {
            LoginScreen(
                onNavigateToHome = navController,
                onNavigateToSignUp = { navController.navigate("signup") },
            )
        }

        composable(
            "main?id={id}&pw={pw}&nickname={nickname}&mbti={mbti}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("pw") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("nickname") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("mbti") {
                    type = NavType.StringType
                    defaultValue = ""
                },
            ),
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            val pw = backStackEntry.arguments?.getString("pw")
            val nickname = backStackEntry.arguments?.getString("nickname")
            val mbti = backStackEntry.arguments?.getString("mbti")
            MainScreen(id.toString(), pw.toString(), nickname.toString(), mbti.toString())
        }
        composable("signup") {
            SignUpScreen(onNavigateToSignIn = navController)
        }
    }
}