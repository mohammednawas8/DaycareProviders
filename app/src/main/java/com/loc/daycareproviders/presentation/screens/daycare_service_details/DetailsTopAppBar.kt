package com.loc.daycareproviders.presentation.screens.daycare_service_details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.BlueButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopAppBar(
    onBackClick: () -> Unit,
) {

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {},
        navigationIcon = {
            BlueButton(text = stringResource(id = R.string.back), onClick = onBackClick)
        }
    )

}

@Preview
@Composable
fun DetailsTopAppBarPreview() {
    DetailsTopAppBar {

    }
}