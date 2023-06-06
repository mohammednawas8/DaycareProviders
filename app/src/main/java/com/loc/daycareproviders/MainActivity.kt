package com.loc.daycareproviders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.loc.daycareproviders.presentation.navigation.NavGraph
import com.loc.daycareproviders.presentation.screens.chatting.ChattingScreen
import com.loc.daycareproviders.ui.theme.DaycareProvidersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DaycareProvidersTheme {
                NavGraph()
//                BrowseDaycareServicesScreen()
//               DaycareServiceDetails()
//                ChattingScreen()
            }
        }
    }
}