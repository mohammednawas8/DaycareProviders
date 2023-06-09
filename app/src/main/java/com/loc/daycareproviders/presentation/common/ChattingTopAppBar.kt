package com.loc.daycareproviders.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.loc.daycareproviders.ui.Dimens.CHATTING_TOP_APPBAR_ELEVATION

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChattingTopAppBar(
    text: String,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = CHATTING_TOP_APPBAR_ELEVATION),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        title = {
            Text(text = text, maxLines = 1, color = Color.Black)
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.White)

    )
}

@Preview(showBackground = true)
@Composable
fun ChattingTopAppBarPreview() {
    ChattingTopAppBar(text = "Mohammad Nawas") {

    }
}