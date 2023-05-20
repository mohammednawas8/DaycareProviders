package com.loc.daycareproviders.presentation.screens.daycare_provider

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.DefaultUserScreen

@Composable
fun DaycareProviderScreen() {
    DefaultUserScreen(
        username = "Name",
        firstButtonText = stringResource(id = R.string.button),
        secondButtonText = stringResource(id = R.string.button),
        firstOptionText = stringResource(id = R.string.add_service),
        secondOptionText = stringResource(id = R.string.direct_messages),
        firstIcon = R.drawable.videoconference,
        secondIcon = R.drawable.videoconference,
        onFirstButtonClick = {

        },
        onSecondButtonClick = {

        }
    )
}