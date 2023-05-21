package com.loc.daycareproviders.presentation.screens.add_daycare_service

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.BlueButton
import com.loc.daycareproviders.presentation.common.ServiceFeatureCard
import com.loc.daycareproviders.ui.Dimens.MEDIUM_PADDING
import com.loc.daycareproviders.ui.Dimens.SERVICE_FEATURE_CARD_SIZE
import com.loc.daycareproviders.ui.Dimens.SMALL_PADDING
import com.loc.daycareproviders.ui.theme.Blue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

@Composable
fun AddDaycareService() {

    var addImagesDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var descriptionDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var priceDialog by rememberSaveable {
        mutableStateOf(false)
    }

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
                onClick = { addImagesDialog = true },
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
                    onClick = { descriptionDialog = true },
                )
                Spacer(modifier = Modifier.weight(1f))
                ServiceFeatureCard(
                    modifier = Modifier
                        .weight(5f)
                        .height(SERVICE_FEATURE_CARD_SIZE),
                    icon = R.drawable.price_tag,
                    text = stringResource(id = R.string.add_price),
                    onClick = { priceDialog = true },
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

    if (addImagesDialog) {
        AddImagesDialog(
            onCameraClick = { /*TODO: Launch the camera*/ },
            onGalleryClick = { /*TODO: Launch the gallery*/ },
            onDismiss = { addImagesDialog = false }
        )
    }

    if (descriptionDialog) {
        AddDescriptionDialog(
            onDismiss = { descriptionDialog = false },
            onDoneClick = { description ->
                /*TODO: save the description in the viewModel*/
            }
        )
    }

    if (priceDialog) {
        AddPriceDialog(onDismiss = {
            priceDialog = false
        },
            onDoneClick = { price, currency ->
                /*TODO: save the price in the viewModel*/
            }
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