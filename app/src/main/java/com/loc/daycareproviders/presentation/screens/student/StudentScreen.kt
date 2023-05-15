package com.loc.daycareproviders.presentation.screens.student

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.DefaultUserScreen
import com.loc.daycareproviders.ui.theme.LightGreen

@Composable
fun StudentScreen() {

    DefaultUserScreen(
        firstButtonText = stringResource(id = R.string.education),
        secondButtonText = stringResource(id = R.string.my_results),
        firstIcon = R.drawable.videoconference,
        secondIcon = R.drawable.results,
        backgroundColor = LightGreen,
        onFirstButtonClick = { /*TODO*/ },
        onSecondButtonClick = { /*TODO*/ },
        onLogoutClick = { /*TODO*/ },
        onToolsClick = {/*TODO*/ }
    )
}