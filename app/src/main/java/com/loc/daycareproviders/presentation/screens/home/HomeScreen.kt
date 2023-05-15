package com.loc.daycareproviders.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigate: (route: String) -> Unit,
) {

    val state = viewModel.state.value

    LaunchedEffect(key1 = viewModel.navigation) {
        viewModel.navigation.collect { route ->
            navigate(route)
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        state.error?.let {
            Text(text = it)
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                color = Color.Blue,
                strokeWidth = 3.dp
            )
        }
    }

}