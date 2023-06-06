package com.loc.daycareproviders.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.loc.daycareproviders.R
import com.loc.daycareproviders.ui.Dimens.ACCOUNT_OPTION_ICON
import com.loc.daycareproviders.ui.Dimens.MEDIUM_PADDING
import com.loc.daycareproviders.ui.Dimens.ROUNDED_CORNER
import com.loc.daycareproviders.ui.Dimens.SMALL_PADDING
import com.loc.daycareproviders.ui.Dimens.USER_OPTION_BORDER
import com.loc.daycareproviders.ui.theme.Blue

@Composable
fun DefaultUserScreen(
    username: String,
    firstButtonText: String,
    secondButtonText: String,
    firstOptionText: String,
    secondOptionText: String,
    @DrawableRes firstIcon: Int,
    @DrawableRes secondIcon: Int,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            painter = painterResource(id = R.drawable.home_background),
            contentDescription = stringResource(
                id = R.string.background
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.welcome) + username,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = SMALL_PADDING, top = MEDIUM_PADDING),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(SMALL_PADDING))
            Text(
                text = stringResource(id = R.string.choose_service),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = SMALL_PADDING, top = MEDIUM_PADDING),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(MEDIUM_PADDING))

            UserOption(
                modifier = Modifier.fillMaxWidth(0.8f),
                buttonText = firstButtonText,
                buttonColor = Blue,
                icon = firstIcon,
                text = firstOptionText,
                onClick = onFirstButtonClick
            )

            Spacer(modifier = Modifier.height(SMALL_PADDING))

            UserOption(
                modifier = Modifier.fillMaxWidth(0.8f),
                buttonText = secondButtonText,
                buttonColor = Blue,
                icon = secondIcon,
                text = secondOptionText,
                onClick = onSecondButtonClick
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SMALL_PADDING)
                .padding(bottom = SMALL_PADDING), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DefaultUserButton(
                onClick = onLogoutClick,
                buttonColor = Blue.copy(alpha = 0.8f),
                buttonText = stringResource(id = R.string.logout)
            )

        }


    }
}


@Composable
fun UserOption(
    modifier: Modifier = Modifier,
    buttonText: String,
    text: String,
    buttonColor: Color,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .graphicsLayer {
                translationX = -6f
            }
            .drawWithContent {
                val height = size.height
                drawRoundRect(
                    color = Color.Black,
                    style = Stroke(width = USER_OPTION_BORDER.value),
                    cornerRadius = CornerRadius(ROUNDED_CORNER.value, ROUNDED_CORNER.value)
                )
                drawRect(
                    color = Color.White,
                    topLeft = Offset.Zero,
                    size = Size(width = USER_OPTION_BORDER.value + 5f, height = height)
                )
                drawContent()
            }

    ) {
        Row(
            modifier = Modifier.padding(all = SMALL_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(ACCOUNT_OPTION_ICON)
                    .weight(2f)
            )
            Column(
                modifier = Modifier
                    .padding(start = SMALL_PADDING)
                    .weight(8f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = text,
                    color = Color.Black.copy(alpha = 0.8f),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
                DefaultUserButton(
                    onClick = onClick,
                    buttonColor = buttonColor,
                    buttonText = buttonText
                )
            }
        }
    }
}

@Composable
fun DefaultUserButton(onClick: () -> Unit, buttonColor: Color, buttonText: String) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = Color.White
        )
    ) {
        Text(text = buttonText)
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, widthDp = 300)
@Composable
fun DefaultUserScreenPreview() {
    DefaultUserScreen(
        username = "Mohammad",
        firstButtonText = stringResource(id = R.string.button),
        secondButtonText = stringResource(id = R.string.button),
        firstOptionText = stringResource(id = R.string.browse),
        secondOptionText = stringResource(id = R.string.browse),
        firstIcon = R.drawable.videoconference,
        secondIcon = R.drawable.videoconference,
        onFirstButtonClick = { /*TODO*/ },
        onLogoutClick = {},
        onSecondButtonClick = {})
}

@Preview(showBackground = true)
@Composable
fun UserOptionPreview() {
    UserOption(
        buttonText = stringResource(id = R.string.button),
        text = stringResource(id = R.string.browse),
        buttonColor = Blue,
        icon = R.drawable.videoconference,
        onClick = {}
    )
}
