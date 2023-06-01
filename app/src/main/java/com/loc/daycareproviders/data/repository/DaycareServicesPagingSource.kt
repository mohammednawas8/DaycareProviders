package com.loc.daycareproviders.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.daycareproviders.domain.model.DaycareService
import com.loc.daycareproviders.domain.repository.DaycareServiceRepository

class DaycareServicesPagingSource(
    private val daycareServiceRepository: DaycareServiceRepository
) : PagingSource<Int, DaycareService>() {

    private var oldDaycareServices =
        mutableListOf<DaycareService>() // Used to determine if we should stop the paging.

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DaycareService> {
        val page = params.key ?: 1
        val limit = page * 3L
        return try {
            val daycareServices = daycareServiceRepository.getDaycareServices(limit = limit)
            val endPaging = daycareServices == oldDaycareServices
            val filteredServices = daycareServices.filter {
                it !in oldDaycareServices
            }
            oldDaycareServices.clear()
            oldDaycareServices.addAll(daycareServices)
            LoadResult.Page(
                data = filteredServices,
                prevKey = null,
                nextKey = if (endPaging) null else page + 1 // When null -> stop paging
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DaycareService>): Int? {
        return null
    }

}