package com.loc.daycareproviders.presentation.screens.chatting

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.loc.daycareproviders.domain.model.ChattingMessage
import com.loc.daycareproviders.ui.Dimens.ROUNDED_CORNER
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.loc.daycareproviders.ui.Dimens.SMALL_PADDING
import com.loc.daycareproviders.ui.theme.Gray

@Composable
fun ChattingMessageCard(
    modifier: Modifier = Modifier,
    chattingMessage: ChattingMessage,
    backgroundColor: Color,
    textColor: Color
) {

    val date = remember {
        chattingMessage.date
    }

    val messageDate = remember {
        "${date.dayOfMonth}/${date.month}/${date.year} ${date.dayOfWeek} ${date.hour}:${date.minute}"
    }

    var isDateVisible by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(ROUNDED_CORNER))
            .background(color = backgroundColor)
            .clickable { isDateVisible = !isDateVisible }
            .animateContentSize()
    ) {
        Column(modifier = Modifier.padding(all = SMALL_PADDING)) {
            Text(text = chattingMessage.text, fontSize = 16.sp,color = textColor)
            AnimatedVisibility(visible = isDateVisible) {
                Text(text = messageDate, fontSize = 12.sp, color = textColor.copy(alpha = 0.7f))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ChattingMessageCardPreview() {
    ChattingMessageCard(
        chattingMessage = ChattingMessage("Hi, I have a question"),
        backgroundColor = Gray,
        textColor = Color.Black
    )
}











