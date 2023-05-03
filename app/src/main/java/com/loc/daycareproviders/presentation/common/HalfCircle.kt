package com.loc.daycareproviders.presentation.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HalfCircle(
    modifier: Modifier = Modifier,
    color: Color,
) {

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = color,
                startAngle = -180f,
                sweepAngle = -180f,
                style = Fill,
                useCenter = true,
                topLeft = Offset(x = 0f, y = -size.height)
            )
        }
    }


}

@Preview
@Composable
fun HalfCirclePreview() {
    HalfCircle(
        color = Color.Green,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
    )
}