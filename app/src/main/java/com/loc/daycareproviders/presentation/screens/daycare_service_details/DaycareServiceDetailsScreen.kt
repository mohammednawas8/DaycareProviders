package com.loc.daycareproviders.presentation.screens.daycare_service_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.BlueButton
import com.loc.daycareproviders.presentation.common.StandardScreen
import com.loc.daycareproviders.ui.Dimens.ICON_SIZE
import com.loc.daycareproviders.ui.Dimens.MEDIUM_PADDING
import com.loc.daycareproviders.ui.Dimens.SMALL_PADDING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaycareServiceDetailsScreen(
    viewModel: DaycareServiceDetailsViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
) {

    val state = viewModel.state.value

    StandardScreen(queue = state.queue, removeUiComponent = viewModel::removeUiComponent) {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                DetailsTopAppBar(
                    onBackClick = navigateUp
                )
            },
            bottomBar = {
                if (!state.isLoading) {
                    BlueButton(
                        text = stringResource(id = R.string.contact_provider),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = SMALL_PADDING)
                    ) {
                        /*TODO: Navigate to chatting screen*/
                    }
                }
            }
        ) {
            val bottomPadding = it.calculateBottomPadding()
            val topPadding = it.calculateTopPadding()

            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        color = Color.Blue,
                        strokeWidth = 3.dp
                    )
                }
            } else {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = topPadding, bottom = bottomPadding)
                ) {
                    ImagesViewer(
                        images = state.daycareService?.images ?: listOf(),
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(ratio = 16/9f)
                            .padding(horizontal = SMALL_PADDING)
                    )

                    Spacer(modifier = Modifier.height(MEDIUM_PADDING))

                    Column(
                        modifier = Modifier
                            .verticalScroll(state = rememberScrollState())
                            .fillMaxWidth()
                            .weight(1f),

                        ) {
                        DescriptionCard(
                            name = state.daycareService?.providerName ?: "",
                            description = state.daycareService?.description ?: "",
                            modifier = Modifier
                                .padding(horizontal = SMALL_PADDING)
                                .fillMaxWidth(fraction = 0.7f)
                        )
                        Spacer(modifier = Modifier.height(MEDIUM_PADDING))
                        Row(
                            modifier = Modifier.padding(horizontal = SMALL_PADDING)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.price_tag),
                                contentDescription = stringResource(
                                    id = R.string.price
                                ),
                                modifier = Modifier.size(ICON_SIZE * 1.3f)
                            )
                            Spacer(modifier = Modifier.width(SMALL_PADDING))
                            Text(text = "${stringResource(id = R.string.price)}: ${state.daycareService?.price ?: 0.0} per day")
                        }
                    }

                }
            }
        }
    }
}