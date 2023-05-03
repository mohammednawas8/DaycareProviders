package com.loc.daycareproviders.presentation.screens.choose_accounts

import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import com.loc.daycareproviders.ui.Dimens.ACCOUNT_OPTION_ICON
import com.loc.daycareproviders.ui.Dimens.ACCOUNT_OPTION_ITEM
import com.loc.daycareproviders.ui.Dimens.EXTRA_SMALL_PADDING
import com.loc.daycareproviders.ui.Dimens.ROUNDED_CORNER
import com.loc.daycareproviders.ui.Dimens.SMALL_PADDING
import com.loc.daycareproviders.ui.theme.Blue

@Composable
fun UserOptionItem(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    text: String,
    onClick: () -> Unit,
) {

    Column(
        modifier = modifier
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(size = ROUNDED_CORNER))
                .background(color = Blue)
                .size(ACCOUNT_OPTION_ITEM),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(ACCOUNT_OPTION_ICON).padding(all = SMALL_PADDING),
                painter = painterResource(id = iconId),
                contentDescription = stringResource(id = R.string.account_type),
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(EXTRA_SMALL_PADDING))
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun UserOptionItemPreview() {
    UserOptionItem(iconId = R.drawable.ic_teacher, text = "Teacher") {

    }
}