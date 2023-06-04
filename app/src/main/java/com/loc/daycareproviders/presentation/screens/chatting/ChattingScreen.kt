package com.loc.daycareproviders.presentation.screens.chatting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
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
import com.loc.daycareproviders.R
import com.loc.daycareproviders.domain.model.ChattingMessage
import com.loc.daycareproviders.presentation.screens.login.StandardTextField
import com.loc.daycareproviders.ui.Dimens.EXTRA_SMALL_PADDING
import com.loc.daycareproviders.ui.Dimens.SMALL_PADDING
import com.loc.daycareproviders.ui.theme.Blue
import com.loc.daycareproviders.ui.theme.Gray


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChattingScreen() {

    val keyboard = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        ChattingTopAppBar(text = "Mohammad Nawas", onBackClick = {/*TODO: Navigate up*/})
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            reverseLayout = true,
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(100) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    if (it % 2 == 0) {
                        ChattingMessageCard(
                            chattingMessage = ChattingMessage("Hello how are you $it"),
                            backgroundColor = Blue,
                            modifier = Modifier.align(Alignment.TopEnd),
                            textColor = Color.White
                        )
                    } else {
                        ChattingMessageCard(
                            chattingMessage = ChattingMessage("Hello how are you $it"),
                            backgroundColor = Gray,
                            modifier = Modifier.align(Alignment.TopStart),
                            textColor = Color.Black
                        )
                    }
                }
            }
        }
        StandardTextField(
            modifier = Modifier
                .fillMaxWidth()
                .imePadding(),
            text = "Text",
            label = stringResource(id = R.string.write_message),
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Go,
            trailingIcon = R.drawable.ic_next,
            onValueChanged = {

            },
            onGo = {

            },
            onTrailingIconClick = {}
        )
    }
}
