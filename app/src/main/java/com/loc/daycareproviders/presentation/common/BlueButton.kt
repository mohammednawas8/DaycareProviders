package com.loc.daycareproviders.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.loc.daycareproviders.ui.Dimens.ROUNDED_CORNER
import com.loc.daycareproviders.ui.theme.Blue
import com.loc.daycareproviders.ui.theme.DaycareProvidersTheme

@Composable
fun BlueButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {

    Button(
        modifier = modifier,
        onClick = onClick,
        content = { Text(text = text, fontSize = MaterialTheme.typography.bodyMedium.fontSize) },
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = ROUNDED_CORNER),
        enabled = enabled
    )
}

@Preview(showBackground = true)
@Composable
fun BlueButtonPreview() {
    DaycareProvidersTheme {
        BlueButton(text = "Login") {

        }
    }

}