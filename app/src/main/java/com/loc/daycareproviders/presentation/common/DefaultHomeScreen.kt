package com.loc.daycareproviders.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loc.daycareproviders.R
import com.loc.daycareproviders.ui.Dimens
import com.loc.daycareproviders.ui.theme.LightBlue
import com.loc.daycareproviders.ui.theme.Orange

@Composable
fun DefaultHomeScreen(
    firstButtonText: String,
    secondButtonText: String,
    @DrawableRes firstIcon: Int,
    @DrawableRes secondIcon: Int,
    backgroundColor: Color,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onToolsClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
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

        Spacer(modifier = Modifier.height(Dimens.SMALL_PADDING))

        OptionItem(
            icon = firstIcon,
            buttonText = firstButtonText,
            backgroundColor = backgroundColor,
            onButtonClick = onFirstButtonClick
        )

        Spacer(modifier = Modifier.height(Dimens.SMALL_PADDING))

        OptionItem(
            icon = secondIcon,
            buttonText = secondButtonText,
            backgroundColor = backgroundColor,
            onButtonClick = onSecondButtonClick
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.EXTRA_SMALL_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween,

        ) {
            BottomOption(
                icon = R.drawable.logout,
                text = stringResource(id = R.string.logout),
                onClick = onLogoutClick
            )
            BottomOption(
                icon = R.drawable.toolbox,
                text = stringResource(id = R.string.tools),
                onClick = onToolsClick
            )
        }

    }
}

@Composable
fun BottomOption(
    @DrawableRes icon: Int,
    text: String,
    onClick: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = R.string.logout),
            modifier = Modifier.size(Dimens.BOTTOM_OPTION_ICON_SIZE)
        )

        HomePageButton(text = text, onClick = onClick)
    }
}

@Composable
fun OptionItem(
    @DrawableRes icon: Int,
    buttonText: String,
    backgroundColor: Color,
    onButtonClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .height(Dimens.OPTION_ITEM_SIZE)
            .aspectRatio(ratio = 1f)
            .clip(RoundedCornerShape(size = Dimens.ROUNDED_CORNER))
            .border(
                width = 2.dp,
                color = Color.Black.copy(alpha = 0.8f),
                shape = RoundedCornerShape(size = Dimens.ROUNDED_CORNER)
            )
            .background(color = backgroundColor), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(ratio = 1f)
            )
            HomePageButton(
                text = buttonText,
                onClick = onButtonClick,
            )
        }
    }
}

@Composable
fun HomePageButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {

    Button(
        modifier = modifier,
        onClick = onClick,
        content = { Text(text = text, fontSize = MaterialTheme.typography.bodyMedium.fontSize) },
        colors = ButtonDefaults.buttonColors(
            containerColor = Orange, contentColor = Color.White
        ),
        shape = CircleShape,
    )
}

@Preview
@Composable
fun HomePageButtonPreview() {
    HomePageButton(text = "Add Child Info") {

    }
}

@Preview
@Composable
fun OptionItem() {
    OptionItem(
        icon = R.drawable.videoconference,
        buttonText = "Add Child info",
        backgroundColor = LightBlue
    ) {

    }
}