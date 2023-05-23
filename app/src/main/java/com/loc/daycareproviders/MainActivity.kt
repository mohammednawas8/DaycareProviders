package com.loc.daycareproviders

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.loc.daycareproviders.presentation.navigation.NavGraph
import com.loc.daycareproviders.ui.theme.DaycareProvidersTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

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
