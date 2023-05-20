package com.loc.daycareproviders.presentation.screens.add_daycare_service

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.BlueButton
import com.loc.daycareproviders.ui.Dimens.MEDIUM_PADDING
import com.loc.daycareproviders.ui.Dimens.ROUNDED_CORNER
import com.loc.daycareproviders.ui.Dimens.SERVICE_FEATURE_CARD_SIZE
import com.loc.daycareproviders.ui.Dimens.SMALL_PADDING
import com.loc.daycareproviders.ui.Dimens.USER_OPTION_BORDER
import com.loc.daycareproviders.ui.theme.Blue

@Composable
fun AddDaycareService() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MEDIUM_PADDING),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.add_service),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Blue.copy(alpha = 0.8f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(SMALL_PADDING))
            Divider(modifier = Modifier.fillMaxWidth(0.9f))
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.9f)
                .padding(top = SMALL_PADDING),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ServiceFeatureCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(SERVICE_FEATURE_CARD_SIZE),
                icon = R.drawable.add,
                text = stringResource(id = R.string.add_images),
                onClick = {/*TODO: Pick Images*/ },
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = SMALL_PADDING),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ServiceFeatureCard(
                    modifier = Modifier
                        .weight(5f)
                        .height(SERVICE_FEATURE_CARD_SIZE),
                    icon = R.drawable.document,
                    text = stringResource(id = R.string.add_description),
                    onClick = {/*TODO: Pick Images*/ },
                )
                Spacer(modifier = Modifier.weight(1f))
                ServiceFeatureCard(
                    modifier = Modifier
                        .weight(5f)
                        .height(SERVICE_FEATURE_CARD_SIZE),
                    icon = R.drawable.price_tag,
                    text = stringResource(id = R.string.add_price),
                    onClick = {/*TODO: Pick Images*/ },
                )
            }

        }

        BlueButton(
            modifier = Modifier.fillMaxWidth(fraction = 0.9f),
            text = stringResource(id = R.string.publish_service),
            onClick = {

            }
        )

    }
}

@Composable
fun ServiceFeatureCard(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
    onClick: () -> Unit,
) {

    Column(
        modifier = modifier
            .size(0.dp) //In case this is called without this size it will cover the entire parent's size because we using weight = 1
            .clip(RoundedCornerShape(ROUNDED_CORNER))
            .background(Color.Gray.copy(alpha = 0.2f))
            .border(
                width = 2.dp,
                color = Color.Black.copy(alpha = 0.7f),
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

@Preview(showBackground = true)
@Preview(showBackground = true, widthDp = 200, heightDp = 400)
@Composable
fun AddDaycareServicePreview() {
    AddDaycareService()
}

@Preview(showBackground = true)
@Composable
fun ServiceCardPreview() {
    ServiceFeatureCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        icon = R.drawable.add,
        text = "Add images",
        onClick = {}
    )
}