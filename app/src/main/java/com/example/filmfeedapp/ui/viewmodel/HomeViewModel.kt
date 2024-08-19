package com.example.filmfeedapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.usecase.GetMoviesOrderByRateUseCase
import com.example.domain.usecase.SearchMoviesUseCase
import com.example.filmfeedapp.ui.model.BaseUiState
import com.example.filmfeedapp.ui.model.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesOrderByRateUseCase: GetMoviesOrderByRateUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
): ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.OnHomeView())
    val homeUiState = _homeUiState.asStateFlow()


    init {
        getMoviesOrderByRate()
    }


    fun getMoviesOrderByRate() {
        viewModelScope.launch {
            _homeUiState.update {
                HomeUiState.OnHomeView(
                    getMoviesOrderByRateUseCase().cachedIn(viewModelScope)
                )
            }
        }
    }


    fun searchMovies(searchQuery: String) {
        if (searchQuery.isBlank()) {
            getMoviesOrderByRate()
        }
        else {
            viewModelScope.launch {
                _homeUiState.update {
                    HomeUiState.OnSearchView(
                        searchQuery,
                        searchMoviesUseCase(searchQuery).cachedIn(viewModelScope)
                    )
                }
            }
        }
    }

}