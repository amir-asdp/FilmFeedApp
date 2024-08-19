package com.example.filmfeedapp.ui.model

import androidx.paging.PagingData
import com.example.data.model.common.MovieBrief
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

sealed class HomeUiState(val searchText: String) {

    data class OnHomeView(
        val moviesOrderByRatePagingItems: Flow<PagingData<MovieBrief>> = MutableStateFlow(PagingData.empty())
    ) : HomeUiState("")

    data class OnSearchView(
        val searchQuery: String = "",
        val searchPagingItems: Flow<PagingData<MovieBrief>> = MutableStateFlow(PagingData.empty())
    ) : HomeUiState(searchQuery)

}