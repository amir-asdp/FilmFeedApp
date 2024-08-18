package com.example.filmfeedapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.model.common.MovieBrief
import com.example.domain.usecase.GetMoviesOrderByRateUseCase
import com.example.domain.usecase.SearchMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesOrderByRateUseCase: GetMoviesOrderByRateUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
): ViewModel() {

    private val _homeUiState: MutableStateFlow<PagingData<MovieBrief>> = MutableStateFlow(PagingData.empty())
    private val _searchUiState: MutableStateFlow<PagingData<MovieBrief>> = MutableStateFlow(PagingData.empty())
    val homeUiState = _homeUiState.asStateFlow()
    val searchUiState = _searchUiState.asStateFlow()


    init {
        getMoviesOrderByRate()
    }


    fun getMoviesOrderByRate() {
        viewModelScope.launch {
            getMoviesOrderByRateUseCase()
                .cachedIn(viewModelScope)
                .collect{
                    _homeUiState.emit(it)
                }
        }
    }


    fun searchMovies(searchQuery: String) {
        viewModelScope.launch {
            searchMoviesUseCase(searchQuery)
                .cachedIn(viewModelScope)
                .collect{
                    _searchUiState.emit(it)
                }
        }
    }

}