package com.loc.daycareproviders.presentation.screens.daycare_provider

import androidx.compose.runtime.Composable
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.DefaultUserScreen

@Composable
fun DaycareProviderScreen() {
    DefaultUserScreen(
        username = "Name",
        firstButtonText = "Text",
        secondButtonText = "Text",
        firstOptionText = "Text",
        secondOptionText = "Text",
        firstIcon = R.drawable.videoconference,
        secondIcon = R.drawable.videoconference,
        onFirstButtonClick = { /*TODO*/ }) {
    }
}