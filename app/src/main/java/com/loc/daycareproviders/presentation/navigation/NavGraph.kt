package com.loc.daycareproviders.presentation.navigation

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.loc.daycareproviders.presentation.screens.choose_accounts.ChooseAccountsScreen
import com.loc.daycareproviders.presentation.screens.home.HomeScreen
import com.loc.daycareproviders.presentation.screens.login.LoginScreen
import com.loc.daycareproviders.presentation.screens.parent.ParentScreen
import com.loc.daycareproviders.presentation.screens.register.RegisterScreen
import com.loc.daycareproviders.presentation.screens.splash.SplashScreen
import com.loc.daycareproviders.presentation.screens.student.StudentScreen
import com.loc.daycareproviders.presentation.screens.teacher.TeacherScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navigate = { route ->
                navController.popBackStack()
                navController.navigate(route)
            }
            )
        }
        composable(route = Screen.ChooseAccountScreen.route) {
            ChooseAccountsScreen(navigate = { route ->
                navController.navigate(route)
            })
        }
        composable(
            route = Screen.LoginScreen.route,
            arguments = Screen.LoginScreen.arguments
        ) {
            val accountType = it.arguments?.getString("accountType") ?: "Unknown"
            LoginScreen(navigate = { route ->
                navController.navigate(route)
            })
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                navigate = { route ->
                    navController.popBackStack()
                    navController.navigate(route)
                }
            )
        }

        composable(route = Screen.StudentScreen.route) {
            StudentScreen()
        }

        composable(route = Screen.TeacherScreen.route) {
            TeacherScreen()
        }

        composable(route = Screen.ParentScreen.route) {
            ParentScreen()
        }
    }

}