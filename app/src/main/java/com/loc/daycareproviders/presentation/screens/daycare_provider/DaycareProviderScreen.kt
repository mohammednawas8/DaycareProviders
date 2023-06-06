package com.loc.daycareproviders.presentation.screens.daycare_provider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.DefaultUserScreen
import com.loc.daycareproviders.presentation.common.UserOptionsViewModel
import com.loc.daycareproviders.presentation.navigation.Screen

@Composable
fun DaycareProviderScreen(
    viewModel: UserOptionsViewModel = hiltViewModel(),
    navigate: (String) -> Unit,
) {

    val state = viewModel.state.value

    LaunchedEffect(key1 = viewModel.navigation) {
        viewModel.navigation.collect {
            navigate(it)
        }
    }

    DefaultUserScreen(
        username = state.name,
        firstButtonText = stringResource(id = R.string.button),
        secondButtonText = stringResource(id = R.string.button),
        firstOptionText = stringResource(id = R.string.add_service),
        secondOptionText = stringResource(id = R.string.direct_messages),
        firstIcon = R.drawable.videoconference,
        secondIcon = R.drawable.videoconference,
        onFirstButtonClick = {
            navigate(Screen.AddDaycareServiceScreen.route)
        },
        onSecondButtonClick = {
            navigate(Screen.DirectMessages.route)
        },
        onLogoutClick = {
            viewModel.logout()
        },

    )
}