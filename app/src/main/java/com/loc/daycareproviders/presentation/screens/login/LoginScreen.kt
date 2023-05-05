package com.loc.daycareproviders.presentation.screens.login

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.BlueButton
import com.loc.daycareproviders.presentation.common.HalfCircle
import com.loc.daycareproviders.ui.Dimens.MEDIUM_PADDING
import com.loc.daycareproviders.ui.theme.Teal

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
) {

    val state = viewModel.state.value
    val passwordFocusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(state = scrollState, orientation = Orientation.Vertical),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HalfCircle(
            color = Teal,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.20f)
                .scale(1.2f)
                .weight(0.2f),
        )

        Spacer(modifier = Modifier.weight(0.2f))

        Column(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.8f)
                .fillMaxHeight(fraction = 0.8f)
                .weight(0.45f),
        ) {
            Column(modifier = Modifier.weight(1f)) {
                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.email,
                    label = stringResource(id = R.string.email),
                    trailingIcon = R.drawable.ic_person,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    onValueChanged = viewModel::updateEmail,
                    onNext = { passwordFocusRequester.requestFocus() }
                )

                Spacer(modifier = Modifier.height(MEDIUM_PADDING))

                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.password,
                    focusRequester = passwordFocusRequester,
                    label = stringResource(id = R.string.password),
                    trailingIcon = if (state.isPasswordVisible) R.drawable.invisible_password else R.drawable.visible_password,
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Go,
                    onValueChanged = viewModel::updatePassword,
                    onTrailingIconClick = viewModel::changePasswordVisibility,
                    onGo = {
                        viewModel.login() /*TODO: Change the account type to the selected one*/
                        keyboard?.hide()
                    }
                )
            }


            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth(),
                horizontalAlignment = CenterHorizontally
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        color = Color.Blue,
                        strokeWidth = 3.dp
                    )
                } else {
                    BlueButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = stringResource(id = R.string.login),
                        onClick = viewModel::login
                    )
                }
            }


        }
    }
}