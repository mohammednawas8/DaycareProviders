package com.loc.daycareproviders.presentation.screens.add_daycare_service

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.BlueButton
import com.loc.daycareproviders.presentation.common.ServiceFeatureCard
import com.loc.daycareproviders.ui.Dimens.MEDIUM_PADDING
import com.loc.daycareproviders.ui.Dimens.ROUNDED_CORNER
import com.loc.daycareproviders.ui.Dimens.SMALL_PADDING
import com.loc.daycareproviders.ui.theme.Gray
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun AddServiceDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit,
) {

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(color = Color.Transparent)
        ) {
            Column {
                BlueButton(text = stringResource(id = R.string.cancel), onClick = onDismiss)
                Spacer(modifier = Modifier.height(5.dp))
                Card(
                    modifier = Modifier.fillMaxSize(),
                    colors = CardDefaults.cardColors(
                        contentColor = Gray
                    ),
                    shape = RoundedCornerShape(ROUNDED_CORNER)
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
fun AddImagesDialog(
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit,
    onDismiss: () -> Unit,
) {
    AddServiceDialog(onDismiss = onDismiss) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            ServiceFeatureCard(
                icon = R.drawable.camera,
                text = stringResource(id = R.string.camera),
                onClick = onCameraClick,
                background = Color.Transparent,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(end = MEDIUM_PADDING),
                borderColor = Color.Transparent
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .background(Gray)
                    .width(2.dp)
                    .align(alignment = CenterVertically)
            )
            ServiceFeatureCard(
                icon = R.drawable.gallery,
                text = stringResource(id = R.string.gallery),
                onClick = onGalleryClick,
                background = Color.Transparent,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(start = MEDIUM_PADDING),
                borderColor = Color.Transparent
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDescriptionDialog(
    onDismiss: () -> Unit,
    onDoneClick: (String) -> Unit,
) {

    var text by rememberSaveable {
        mutableStateOf("")
    }
    AddServiceDialog(onDismiss = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = SMALL_PADDING)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                placeholder = {
                    Text(text = stringResource(id = R.string.add_description) + "...")
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(size = ROUNDED_CORNER)
            )
            BlueButton(
                text = stringResource(id = R.string.done),
                onClick = { onDoneClick(text) },
                modifier = Modifier.align(CenterHorizontally)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPriceDialog(
    onDismiss: () -> Unit,
    onDoneClick: (price: Double, currency: String) -> Unit,
) {
    var price by rememberSaveable {
        mutableStateOf("")
    }
    var selectedCurrency by rememberSaveable {
        mutableStateOf("")
    }

    var isMenuExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    val currencies = remember {
        listOf("SAR", "USD")
    }

    AddServiceDialog(onDismiss = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = SMALL_PADDING)
        ) {
            OutlinedTextField(
                value = price,
                onValueChange = {
                    price = it
                },
                shape = RoundedCornerShape(size = ROUNDED_CORNER),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                label = {
                    Text(text = stringResource(id = R.string.add_price) + "...")
                },
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(0.7f)
            )

            Spacer(modifier = Modifier.height(SMALL_PADDING))

            ExposedDropdownMenuBox(
                modifier = Modifier.fillMaxWidth(0.7f),
                expanded = isMenuExpanded,
                onExpandedChange = {
                    isMenuExpanded = it
                }
            ) {
                OutlinedTextField(
                    value = selectedCurrency,
                    onValueChange = {},
                    readOnly = true,
                    placeholder = { Text(text = stringResource(id = R.string.currency)) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isMenuExpanded)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor(),
                    shape = RoundedCornerShape(size = ROUNDED_CORNER),
                )
                ExposedDropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = { isMenuExpanded = false })
                {
                    currencies.forEach { currency ->
                        DropdownMenuItem(
                            text = { Text(text = currency) },
                            onClick = {
                                selectedCurrency = currency
                                isMenuExpanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            BlueButton(
                text = stringResource(id = R.string.done),
                onClick = {
                    if (price == "")
                        return@BlueButton
                    onDoneClick(price.toDouble(), selectedCurrency)
                },
                modifier = Modifier.align(CenterHorizontally)
            )
        }

    }
}

@Preview
@Composable
fun AddServiceDialogPreview() {
    AddServiceDialog(onDismiss = {}, content = {
        Row() {
        }
    }
    )
}

@Preview
@Composable
fun AddImagesDialogPreview() {
    AddImagesDialog(onCameraClick = { /*TODO*/ }, onGalleryClick = { /*TODO*/ }) {

    }
}

@Preview
@Composable
fun AddDescriptionDialogPreview() {
    AddDescriptionDialog(onDismiss = {}, onDoneClick = {})
}

@Preview
@Composable
fun AddPriceDialogPreview() {
    AddPriceDialog(onDismiss = { /*TODO*/ }, onDoneClick = { _, _ -> })
}