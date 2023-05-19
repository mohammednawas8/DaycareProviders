package com.loc.daycareproviders.presentation.screens.normal_user

import androidx.compose.runtime.Composable
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.DefaultUserScreen

@Composable
fun NormalUserScreen() {
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