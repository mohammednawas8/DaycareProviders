package com.loc.daycareproviders.presentation.screens.browse_daycare_services

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.loc.daycareproviders.R
import com.loc.daycareproviders.domain.model.DaycareService
import com.loc.daycareproviders.presentation.common.BlueButton
import com.loc.daycareproviders.ui.Dimens.DAYCARE_CARD_BORDER
import com.loc.daycareproviders.ui.Dimens.EXTRA_SMALL_PADDING
import com.loc.daycareproviders.ui.Dimens.ROUNDED_CORNER
import com.loc.daycareproviders.ui.Dimens.SMALL_PADDING
import com.loc.daycareproviders.ui.Dimens.USER_OPTION_BORDER

@Composable
fun DaycareServiceCard(
    modifier: Modifier = Modifier,
    daycareService: DaycareService,
    onDetailsClick: () -> Unit,
) {

    val shape = remember {
        RoundedCornerShape(size = ROUNDED_CORNER)
    }

    Card(
        modifier = modifier
            .clip(shape = shape)
            .border(
                width = DAYCARE_CARD_BORDER,
                color = Color.Black.copy(alpha = 0.6f),
                shape = shape
            )
            .width(width = 300.dp),
        shape = shape,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Column(
            modifier = Modifier.padding(
                top = EXTRA_SMALL_PADDING,
                start = SMALL_PADDING,
                end = SMALL_PADDING
            ),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            AsyncImage(
                model = daycareService.images[0],
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .clip(shape = shape),
                contentScale = ContentScale.Crop,
            )
            Text(text = "Provider: ${daycareService.providerName}", maxLines = 1)
            Text(
                text = daycareService.description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                BlueButton(text = stringResource(id = R.string.details), onClick = onDetailsClick)
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${stringResource(id = R.string.price)}: " +
                            "${daycareService.price} ${daycareService.currency}\n" +
                            "per day",
                    maxLines = 2
                )
            }
            Spacer(modifier = Modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DaycareServiceCardPreview() {
    DaycareServiceCard(
        daycareService = DaycareService(
            images = listOf("https://j.top4top.io/p_2701f1jo31.jpg"),
            description = "Sample Sample Sample Sample Sample Sample",
            price = 25.0,
            currency = "USD",
        )
    ) {}
}