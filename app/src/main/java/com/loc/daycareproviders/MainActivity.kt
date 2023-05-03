package com.loc.daycareproviders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.loc.daycareproviders.presentation.screens.login.LoginScreen
import com.loc.daycareproviders.presentation.screens.login.LoginViewModel
import com.loc.daycareproviders.ui.theme.DaycareProvidersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<LoginViewModel>()
        setContent {
            DaycareProvidersTheme {
                LoginScreen(viewModel)
            }
        }
    }
}
