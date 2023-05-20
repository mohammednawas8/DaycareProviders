package com.loc.daycareproviders.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
) {

    object SplashScreen : Screen(route = "splash_screen")
    object ChooseAccountScreen : Screen(route = "choose_account_screen")

    object LoginScreen : Screen(
        route = "login_screen/{accountType}",
        arguments = listOf(
            navArgument(
                name = "accountType",
                builder = {
                    type = NavType.StringType
                    nullable = false
                }
            )
        )
    ){
        fun navigate(arg: String): String{
            return "login_screen/$arg"
        }
    }

    object RegisterScreen : Screen(
        route = "register_screen/{accountType}",
        arguments = listOf(
            navArgument(
                name = "accountType",
                builder = {
                    type = NavType.StringType
                    nullable = false
                }
            )
        )
    ){
        fun navigate(arg: String): String{
            return "register_screen/$arg"
        }
    }


    object HomeScreen : Screen(route = "home_screen")

    object NormalUserScreen : Screen(route = "normal_user_screen")
    object DaycareProviderScreen : Screen(route = "daycare_provider_screen")

}