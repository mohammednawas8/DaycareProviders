package com.loc.daycareproviders.presentation.navigation

sealed class Feature(val route: String){

    object Authentication: Feature(route = "authentication")

    object Home: Feature(route = "home")


}
