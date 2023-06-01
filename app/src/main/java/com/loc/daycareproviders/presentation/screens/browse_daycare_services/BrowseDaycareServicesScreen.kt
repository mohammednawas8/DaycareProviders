package com.loc.daycareproviders.presentation.screens.browse_daycare_services

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.loc.daycareproviders.R
import com.loc.daycareproviders.ui.Dimens.SMALL_PADDING
import com.loc.daycareproviders.ui.theme.Blue

@Composable
fun BrowseDaycareServicesScreen(
    viewModel: BrowseDaycareServicesViewModel = hiltViewModel(),
) {

    val daycareServicesState = viewModel.daycareServices.collectAsLazyPagingItems()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(space = SMALL_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.daycare_services),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Blue.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
            contentPadding = PaddingValues(bottom = SMALL_PADDING)
        ) {
            items(daycareServicesState) { daycareService ->
                daycareService?.let {
                    DaycareServiceCard(
                        daycareService = daycareService,
                        onDetailsClick = {
                            //TODO : Navigate to the details screen and send the id of the service as an argument
                        }
                    )
                }

            }
        }
    }


}