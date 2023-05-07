package com.loc.daycareproviders.presentation.screens.splash

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.HalfCircle
import com.loc.daycareproviders.presentation.navigation.Screen
import com.loc.daycareproviders.ui.Dimens.SMALL_PADDING
import com.loc.daycareproviders.ui.theme.Blue
import com.loc.daycareproviders.ui.theme.Teal
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreen(
    navigate: (String) -> Unit,
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
    Box(modifier = Modifier.fillMaxSize()) {

        LaunchedEffect(key1 = Unit){
            delay(2.seconds)
            splashViewModel.navigate.collect { route ->
                navigate(route)
            }
        }

        QuarterCircle(
            color = Teal, modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(100.dp)
                .align(alignment = Alignment.TopStart)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_student),
                contentDescription = stringResource(
                    id = R.string.app_logo
                ),
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(SMALL_PADDING))
            Text(
                text = stringResource(id = R.string.splash_title),
                style = MaterialTheme.typography.titleMedium
            )
        }

        HalfCircle(
            color = Blue,
            modifier = Modifier
                .rotate(degrees = 180f)
                .fillMaxWidth()
                .height(100.dp)
                .scale(scaleX = 1.2f, scaleY = 1f)
                .align(alignment = Alignment.BottomCenter)
        )
    }
}