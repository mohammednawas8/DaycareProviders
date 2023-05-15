package com.loc.daycareproviders.presentation.screens.parent

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.DefaultUserScreen
import com.loc.daycareproviders.ui.theme.LightBlue
import com.loc.daycareproviders.ui.theme.LightPurple

@Composable
fun ParentScreen() {

    DefaultUserScreen(
        firstButtonText = stringResource(id = R.string.add_hw),
        secondButtonText = stringResource(id = R.string.home_nursery),
        firstIcon = R.drawable.user,
        secondIcon = R.drawable.castle,
        backgroundColor = LightBlue,
        onFirstButtonClick = { /*TODO*/ },
        onSecondButtonClick = { /*TODO*/ },
        onLogoutClick = { /*TODO*/ },
        onToolsClick = {/*TODO*/ }
    )
}