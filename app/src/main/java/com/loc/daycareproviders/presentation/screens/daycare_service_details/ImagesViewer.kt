package com.loc.daycareproviders.presentation.screens.daycare_service_details

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loc.daycareproviders.R
import com.loc.daycareproviders.ui.Dimens
import com.loc.daycareproviders.ui.Dimens.ROUNDED_CORNER
import com.loc.daycareproviders.ui.Dimens.SMALL_PADDING
import com.loc.daycareproviders.ui.theme.Blue
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesViewer(
    modifier: Modifier = Modifier,
    images: List<String>,
) {

    val pagerState = rememberPagerState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Row(
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        ImageMover(modifier = Modifier.fillMaxHeight(), icon = R.drawable.ic_back, onClick = {
            scope.launch {
                if (pagerState.currentPage == pagerState.initialPage) {
                    pagerState.animateScrollToPage(page = images.size - 1)
                } else pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
            }
        })

        HorizontalPager(
            modifier = Modifier.weight(1f), pageCount = images.size, state = pagerState
        ) { index ->
            var isLoading by remember {
                mutableStateOf(false)
            }
            AsyncImage(
                model = ImageRequest.Builder(context).data(images[index]).build(),
                contentDescription = null,
                modifier = Modifier.clip(RoundedCornerShape(ROUNDED_CORNER)).fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                onLoading = {
                    isLoading = true
                },
                onSuccess = {
                    isLoading = false
                },
            )
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                    CircularProgressIndicator(
                        color = Color.Blue,
                        strokeWidth = 3.dp,
                    )
                }
            }
        }
        ImageMover(modifier = Modifier.fillMaxHeight(), icon = R.drawable.ic_next, onClick = {
            scope.launch {
                if (pagerState.currentPage == images.size - 1) {
                    pagerState.animateScrollToPage(page = pagerState.initialPage)
                } else pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
            }
        })
    }
}

@Composable
fun ImageMover(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
) {

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(color = Blue)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }

}

@Preview
@Composable
fun ImagesViewerPreview() {
    ImagesViewer(
        images = listOf(
            "https://b.top4top.io/p_2708s7evz1.jpg", "https://d.top4top.io/p_2708m6yvi2.jpg"
        ), modifier = Modifier.padding(horizontal = SMALL_PADDING)
    )
}