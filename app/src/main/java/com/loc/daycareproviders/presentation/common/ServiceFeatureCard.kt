package com.loc.daycareproviders.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.loc.daycareproviders.ui.Dimens.ROUNDED_CORNER
import com.loc.daycareproviders.ui.Dimens.SMALL_PADDING
import com.loc.daycareproviders.ui.theme.Gray

@Composable
fun ServiceFeatureCard(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
    background: Color = Gray,
    borderColor: Color = Color.Black.copy(alpha = 0.7f),
    onClick: () -> Unit,
) {

    Column(
        modifier = modifier
            .size(0.dp) //In case this is called without this size it will cover the entire parent's size because we using weight = 1
            .clip(RoundedCornerShape(ROUNDED_CORNER))
            .background(background)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(ROUNDED_CORNER)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .padding(all = SMALL_PADDING)
        )



        BlueButton(
            text = text,
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = SMALL_PADDING)
        )

    }

}