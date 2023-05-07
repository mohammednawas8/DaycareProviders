package com.loc.daycareproviders.presentation.screens.register

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.BlueButton
import com.loc.daycareproviders.presentation.common.HalfCircle
import com.loc.daycareproviders.presentation.common.StandardScreen
import com.loc.daycareproviders.presentation.screens.login.StandardTextField
import com.loc.daycareproviders.ui.Dimens
import com.loc.daycareproviders.ui.Dimens.MEDIUM_PADDING
import com.loc.daycareproviders.ui.theme.Teal

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    navigateUp:() -> Unit
) {

    val state = viewModel.state.value
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val lastNameFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

    val keyboard = LocalSoftwareKeyboardController.current

    //Registering successes
    LaunchedEffect(key1 = viewModel.navigation) {
        viewModel.navigation.collect {
            Toast.makeText(context, context.getString(R.string.new_register), Toast.LENGTH_SHORT)
                .show()
            navigateUp()
        }
    }
    StandardScreen(queue = state.queue, removeUiComponent = viewModel::removeUiComponent) {

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val maxHeight = constraints.maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState),
        ) {
            HalfCircle(
                color = Teal,
                modifier = Modifier
                    .fillMaxWidth()
                    .height((maxHeight / 20).dp)
                    .scale(scale = 1.2f)
            )


            Column(
                modifier = Modifier
                    .padding(MEDIUM_PADDING)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {

                StandardTextField(modifier = Modifier.fillMaxWidth(),
                    text = state.firstName,
                    label = stringResource(id = R.string.first_name),
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    onValueChanged = viewModel::updateFirstName,
                    onNext = {
                        lastNameFocusRequester.requestFocus()
                    }
                )

                Spacer(modifier = Modifier.height(MEDIUM_PADDING))

                StandardTextField(modifier = Modifier.fillMaxWidth(),
                    text = state.lastName,
                    label = stringResource(id = R.string.last_name),
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    onValueChanged = viewModel::updateLastName,
                    focusRequester = lastNameFocusRequester,
                    onNext = {
                        emailFocusRequester.requestFocus()
                    }
                )

                Spacer(modifier = Modifier.height(MEDIUM_PADDING))

                StandardTextField(modifier = Modifier.fillMaxWidth(),
                    text = state.email,
                    label = stringResource(id = R.string.email),
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    onValueChanged = viewModel::updateEmail,
                    focusRequester = emailFocusRequester,
                    onNext = {
                        passwordFocusRequester.requestFocus()
                    }
                )

                Spacer(modifier = Modifier.height(MEDIUM_PADDING))

                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.password,
                    label = stringResource(id = R.string.password),
                    trailingIcon = if (state.isPasswordVisible) R.drawable.invisible_password else R.drawable.visible_password,
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Go,
                    focusRequester = passwordFocusRequester,
                    onValueChanged = viewModel::updatePassword,
                    onTrailingIconClick = viewModel::changePasswordVisibility,
                    onGo = {
                        viewModel.registerAccount()
                        keyboard?.hide()
                    },
                )

                Spacer(modifier = Modifier.padding(MEDIUM_PADDING))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    if (state.isLoading) {
                        CircularProgressIndicator(
                            color = Color.Blue, strokeWidth = 3.dp
                        )
                    } else {
                        BlueButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.register),
                            onClick = viewModel::registerAccount
                        )
                    }
                }
            }
        }
    }
    }
}
