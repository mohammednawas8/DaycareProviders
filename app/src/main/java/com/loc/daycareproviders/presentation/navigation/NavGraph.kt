package com.loc.daycareproviders.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.loc.daycareproviders.presentation.screens.choose_accounts.ChooseAccountsScreen
import com.loc.daycareproviders.presentation.screens.home.HomeScreen
import com.loc.daycareproviders.presentation.screens.login.LoginScreen
import com.loc.daycareproviders.presentation.screens.register.RegisterScreen
import com.loc.daycareproviders.presentation.screens.splash.SplashScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(
                navigate = { route ->
                    navController.popBackStack()
                    navController.navigate(route)
                }
            )
        }
        //Authentication sub-graph
        navigation(
            startDestination = Screen.ChooseAccountScreen.route,
            route = Feature.Authentication.route
        ) {
            composable(route = Screen.ChooseAccountScreen.route) {
                ChooseAccountsScreen(navigate = { route ->
                    navController.navigate(route)
                })
            }
            composable(
                route = Screen.LoginScreen.route,
                arguments = Screen.LoginScreen.arguments
            ) {
                LoginScreen(navigate = { route ->
                    navController.navigate(route)
                })
            }
            composable(route = Screen.RegisterScreen.route) {
                RegisterScreen(navigateUp = {
                    navController.navigateUp()
                })
            }
        }

        //Home sub-graph
        navigation(
            startDestination = Screen.HomeScreen.route,
            route = Feature.Home.route
        ){
            composable(route = Screen.HomeScreen.route) {
                HomeScreen()
            }
        }

    }

}