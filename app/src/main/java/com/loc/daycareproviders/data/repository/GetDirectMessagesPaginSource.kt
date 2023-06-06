package com.loc.daycareproviders.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.daycareproviders.domain.model.Conversation
import com.loc.daycareproviders.domain.repository.ChattingRepository

class GetDirectMessagesPagingSource(
    private val chattingRepository: ChattingRepository,
) : PagingSource<Int, Conversation>() {

    private val oldConversations = mutableListOf<Conversation>()

    override fun getRefreshKey(state: PagingState<Int, Conversation>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Conversation> {
        val key = params.key ?: 1
        val limit = key * 10L

        try {
            val conversations = chattingRepository.getConversations(limit)
            conversations.forEach{
                Log.d("test",it.toString())
            }
            val endPaging = conversations == oldConversations
            val filteredConversations = conversations.filter {
                it !in oldConversations
            }
            oldConversations.clear()
            oldConversations.addAll(conversations)
            return LoadResult.Page(
                data = filteredConversations,
                prevKey = null,
                nextKey = if (endPaging) null else key + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

    }
}