package com.loc.daycareproviders.presentation.screens.add_daycare_service

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.loc.daycareproviders.presentation.common.StandardScreen

@Composable
fun AddDaycareServiceScreen(
    viewModel: AddDaycareServiceViewModel = hiltViewModel(),
) {

    val state = viewModel.state.value

    var addImagesDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var descriptionDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var priceDialog by rememberSaveable {
        mutableStateOf(false)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { bmp ->
            viewModel.takePicture(bmp)
            addImagesDialog = false

        }
    )

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = {
            viewModel.selectImages(it)
            addImagesDialog = false
        }
    )

    StandardScreen(queue = state.queue, removeUiComponent = viewModel::removeUiComponent) {

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
                    text = "${stringResource(id = R.string.add_images)} (${state.selectedImagesCount})",
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

            if (state.isLoading) {
                CircularProgressIndicator(
                    color = Color.Blue,
                    strokeWidth = 3.dp
                )
            } else {
                BlueButton(
                    modifier = Modifier.fillMaxWidth(fraction = 0.9f),
                    text = stringResource(id = R.string.publish_service),
                    onClick = {
                        viewModel.publishService()
                    }
                )
            }


        }

        if (addImagesDialog) {
            AddImagesDialog(
                onCameraClick = { cameraLauncher.launch(null) },
                onGalleryClick = { galleryLauncher.launch("image/*") },
                onCancel = { addImagesDialog = false }
            )
        }

        if (descriptionDialog) {
            AddDescriptionDialog(
                description = state.description,
                onTextChange = viewModel::changeDescription,
                onCancel = {
                    viewModel.changeDescription("")
                    descriptionDialog = false
                },
                onDone = {
                    descriptionDialog = false
                }
            )
        }

        if (priceDialog) {
            AddPriceDialog(
                price = state.price,
                currency = state.currency,
                onPriceChange = viewModel::changePrice,
                onCurrencyChange = viewModel::changeCurrency,
                onCancel = {
                    viewModel.changePrice(null)
                    viewModel.changeCurrency("")
                    priceDialog = false
                },
                onDoneClick = {
                    priceDialog = false
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, widthDp = 200, heightDp = 400)
@Composable
fun AddDaycareServicePreview() {
    AddDaycareServiceScreen()
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