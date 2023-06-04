package com.loc.daycareproviders.presentation.screens.chatting

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loc.daycareproviders.ui.Dimens.CHATTING_TOP_APPBAR_ELEVATION

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChattingTopAppBar(
    text: String,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth().shadow(elevation = CHATTING_TOP_APPBAR_ELEVATION),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        title = {
            Text(text = text, maxLines = 1)
        },

    )
}

@Preview(showBackground = true)
@Composable
fun ChattingTopAppBarPreview() {
    ChattingTopAppBar(text = "Mohammad Nawas") {

    }
}