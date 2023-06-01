package com.loc.daycareproviders.presentation.screens.browse_daycare_services


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.loc.daycareproviders.data.repository.DaycareServicesPagingSource
import com.loc.daycareproviders.domain.repository.DaycareServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BrowseDaycareServicesViewModel @Inject constructor(
    private val daycareServiceRepository: DaycareServiceRepository,
) : ViewModel() {

    val daycareServices = Pager(
        config = PagingConfig(pageSize = 2),
        pagingSourceFactory = {
            DaycareServicesPagingSource(
                daycareServiceRepository = daycareServiceRepository
            )
        }
    ).flow.cachedIn(viewModelScope)

}