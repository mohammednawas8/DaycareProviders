package com.loc.daycareproviders.presentation.screens.chatting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.ChattingTopAppBar
import com.loc.daycareproviders.presentation.screens.login.StandardTextField
import com.loc.daycareproviders.ui.Dimens.SMALL_PADDING
import com.loc.daycareproviders.ui.theme.Blue


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChattingScreen(
    viewModel: ChattingViewModel = hiltViewModel(),
    navigateUp:() -> Unit
) {

    val keyboard = LocalSoftwareKeyboardController.current
    val state = viewModel.state.value
    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                color = Color.Blue,
                strokeWidth = 3.dp
            )
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),

        ) {
        ChattingTopAppBar(text = state.username, onBackClick = navigateUp)

        if (state.messages.isEmpty()) {
            NoMessages()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                reverseLayout = false,
                contentPadding = PaddingValues(all = SMALL_PADDING),
                verticalArrangement = Arrangement.spacedBy(2.dp, alignment = Alignment.Bottom)
            ) {
                items(state.messages) { message ->
                    Box(modifier = Modifier.fillMaxWidth()) {
                        ChattingMessageCard(
                            chattingMessage = message.chattingMessage,
                            backgroundColor = Blue,
                            modifier = Modifier.align(message.alignment),
                            textColor = Color.White
                        )
                    }
                }
            }
        }
        StandardTextField(
            modifier = Modifier
                .fillMaxWidth()
                .imePadding(),
            text = state.message,
            label = stringResource(id = R.string.write_message),
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Go,
            trailingIcon = R.drawable.ic_next,
            onValueChanged = viewModel::changeMessage,
            onGo = viewModel::sendMessage,
            onTrailingIconClick = viewModel::sendMessage
        )
    }
}

@Composable
fun ColumnScope.NoMessages() {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.no_messages))
    }
}