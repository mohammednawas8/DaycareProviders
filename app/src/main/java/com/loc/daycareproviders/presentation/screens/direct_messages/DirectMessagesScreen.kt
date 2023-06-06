package com.loc.daycareproviders.presentation.screens.direct_messages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.loc.daycareproviders.R
import com.loc.daycareproviders.presentation.common.ChattingTopAppBar
import com.loc.daycareproviders.presentation.common.handlePagingResult
import com.loc.daycareproviders.presentation.navigation.Screen
import com.loc.daycareproviders.ui.Dimens.SMALL_PADDING
import com.loc.daycareproviders.ui.theme.Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DirectMessagesScreen(
    viewModel: DirectMessagesViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
    navigate: (String) -> Unit,
) {

    val conversations = viewModel.converastions.collectAsLazyPagingItems()
    val result = handlePagingResult(items = conversations)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ChattingTopAppBar(
                text = stringResource(id = R.string.direct_messages),
                onBackClick = navigateUp
            )
        },
        containerColor = Color.White,
        contentColor = Color.Unspecified
    ) {
        val topPadding = it.calculateTopPadding()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
            contentPadding = PaddingValues(all = SMALL_PADDING)
        ) {
            items(conversations) { conversation ->
                conversation?.let {
                    ConversationItem(
                        text = conversation.receiverName,
                        onClick = {
                            navigate(
                                Screen.Chatting.navigate(
                                    username = conversation.receiverName,
                                    conversationId = conversation.conversationId
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ConversationItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .background(Gray))
    {
        Text(text = text, modifier = Modifier.padding(all = SMALL_PADDING))
    }
}

@Preview(showBackground = true)
@Composable
fun ConversationItemPreview() {
    ConversationItem(text = "Mohammad Nawas") {


    }
}