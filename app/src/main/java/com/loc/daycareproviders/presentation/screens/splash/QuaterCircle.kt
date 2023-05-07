package com.loc.daycareproviders.presentation.screens.splash

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loc.daycareproviders.ui.theme.DaycareProvidersTheme
import com.loc.daycareproviders.ui.theme.Teal

@Composable
fun QuarterCircle(
    modifier: Modifier = Modifier,
    color: Color,
) {

    Canvas(modifier = modifier) {
        val size = this.size
        drawArc(
            color = color,
            startAngle = -270f,
            sweepAngle = -90f,
            useCenter = true,
            topLeft = Offset(-size.width, -size.height)
        )
    }
}

@Preview
@Composable
fun QuarterCirclePreview() {
    DaycareProvidersTheme {
        QuarterCircle(color = Teal, modifier = Modifier.size(100.dp))
    }
}