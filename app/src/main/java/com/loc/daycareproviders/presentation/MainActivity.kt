package com.loc.daycareproviders.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.loc.daycareproviders.ui.theme.DaycareProvidersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaycareProvidersTheme {

            }
        }
    }
}
