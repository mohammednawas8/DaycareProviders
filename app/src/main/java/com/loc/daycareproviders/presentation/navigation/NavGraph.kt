package com.loc.daycareproviders.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.loc.daycareproviders.presentation.screens.add_daycare_service.AddDaycareServiceScreen
import com.loc.daycareproviders.presentation.screens.choose_accounts.ChooseAccountsScreen
import com.loc.daycareproviders.presentation.screens.daycare_provider.DaycareProviderScreen
import com.loc.daycareproviders.presentation.screens.home.HomeScreen
import com.loc.daycareproviders.presentation.screens.login.LoginScreen
import com.loc.daycareproviders.presentation.screens.normal_user.NormalUserScreen
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
                    navController.navigate(route) {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = Screen.NormalUserScreen.route,
            arguments = Screen.NormalUserScreen.arguments
        ) {
            NormalUserScreen()
        }

        composable(
            route = Screen.DaycareProviderScreen.route,
            arguments = Screen.DaycareProviderScreen.arguments
        ) {
            DaycareProviderScreen(
                navigate = { route ->
                    if (route == Screen.SplashScreen.route) {
                        navController.navigate(route) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    } else {
                        navController.navigate(route)
                    }
                })
        }

        composable(route = Screen.AddDaycareServiceScreen.route) {
            AddDaycareServiceScreen(
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}