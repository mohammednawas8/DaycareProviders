package com.loc.daycareproviders.presentation.screens.direct_messages

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.loc.daycareproviders.data.repository.GetDirectMessagesPagingSource
import com.loc.daycareproviders.domain.repository.ChattingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DirectMessagesViewModel @Inject constructor(
    private val chattingRepository: ChattingRepository,
) : ViewModel() {

    val converastions = Pager(
        config = PagingConfig(pageSize = 2),
        pagingSourceFactory = {
            GetDirectMessagesPagingSource(
                chattingRepository = chattingRepository
            )
        }
    ).flow.cachedIn(viewModelScope)

}