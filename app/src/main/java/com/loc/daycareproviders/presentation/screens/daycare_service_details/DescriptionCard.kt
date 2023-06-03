package com.loc.daycareproviders.presentation.screens.daycare_service_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.ServiceFeatureCard
import com.loc.daycareproviders.ui.Dimens
import com.loc.daycareproviders.ui.Dimens.ICON_SIZE
import com.loc.daycareproviders.ui.Dimens.ROUNDED_CORNER
import com.loc.daycareproviders.ui.Dimens.SMALL_PADDING
import com.loc.daycareproviders.ui.theme.Gray

@Composable
fun DescriptionCard(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
) {
    Row(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.circular_user),
            modifier = Modifier.size(ICON_SIZE * 1.4f),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(SMALL_PADDING))
        Column {
            Text(text = name)
            Divider()
            Spacer(modifier = Modifier.height(SMALL_PADDING))
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(ROUNDED_CORNER))
                    .background(Gray)
            ) {
                Text(text = description, modifier = Modifier.padding(all = SMALL_PADDING))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DescriptionCardPreview() {
    DescriptionCard(
        name = "Mohammad Nawas",
        description = "Home daycare for kids from age 3 - 6 available from sunday to thursday",
        modifier = Modifier.width(200.dp)
    )
}