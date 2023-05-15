package com.loc.daycareproviders.presentation.screens.teacher

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.DefaultUserScreen
import com.loc.daycareproviders.ui.theme.LightPurple

@Composable
fun TeacherScreen() {

    DefaultUserScreen(
        firstButtonText = stringResource(id = R.string.add_hw),
        secondButtonText = stringResource(id = R.string.home_nursery),
        firstIcon = R.drawable.digital,
        secondIcon = R.drawable.house,
        backgroundColor = LightPurple,
        onFirstButtonClick = { /*TODO*/ },
        onSecondButtonClick = { /*TODO*/ },
        onLogoutClick = { /*TODO*/ },
        onToolsClick = {/*TODO*/ }
    )
}