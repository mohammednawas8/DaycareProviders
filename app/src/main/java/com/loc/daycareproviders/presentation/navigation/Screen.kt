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
    ) {
        fun navigate(arg: String): String {
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
    ) {
        fun navigate(arg: String): String {
            return "register_screen/$arg"
        }
    }


    object HomeScreen : Screen(route = "home_screen")

    object NormalUserScreen : Screen(
        route = "normal_user_screen/{name}", arguments = listOf(
            navArgument(
                name = "name"
            ) {
                type = NavType.StringType
                nullable = false
            }
        )
    ) {
        fun navigate(name: String): String {
            return "normal_user_screen/$name"
        }
    }

    object DaycareProviderScreen :
        Screen(route = "daycare_provider_screen/{name}", arguments = listOf(
            navArgument(
                name = "name"
            ) {
                type = NavType.StringType
                nullable = false
            }
        )
        ) {
        fun navigate(name: String): String {
            return "daycare_provider_screen/$name"
        }
    }

    object AddDaycareServiceScreen : Screen(route = "add_daycare_service_screen")
    object BrowseServicesScreen : Screen(route = "browse_daycare_service")
    object DaycareServiceDetails : Screen(
        route = "daycare_service_details/{serviceId}",
        arguments = listOf(
            navArgument(name = "serviceId") {
                type = NavType.StringType
                nullable = false
            })
    ) {
        fun navigate(serviceId: String): String {
            return "daycare_service_details/$serviceId"
        }
    }

    object Chatting : Screen(
        route = "chatting_screen/{username}/{conversationId}",
        arguments = listOf(
            navArgument(
                name = "username"
            ) {
                type = NavType.StringType
                nullable = false
            },
            navArgument(name = "conversationId") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) {
        fun navigate(username: String, conversationId: String):String {
            return "chatting_screen/$username/$conversationId"
        }
    }
}