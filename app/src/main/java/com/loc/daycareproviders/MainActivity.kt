package com.loc.daycareproviders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.loc.daycareproviders.presentation.navigation.NavGraph
import com.loc.daycareproviders.ui.theme.DaycareProvidersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaycareProvidersTheme {
                NavGraph()
//                DefaultHomeScreen(
//                    firstButtonText = "Add Child info",
//                    secondButtonText = "My results",
//                    firstIcon = R.drawable.videoconference,
//                    secondIcon = R.drawable.results,
//                    backgroundColor = LightBlue,
//                    onFirstButtonClick = { /*TODO*/ },
//                    onSecondButtonClick = { /*TODO*/ },
//                    onLogoutClick = { /*TODO*/ }) {
//
//                }
            }
        }
    }
}
