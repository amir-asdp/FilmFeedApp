package com.example.filmfeedapp.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.common.MovieDetail
import com.example.domain.usecase.GetMovieDetailsUseCase
import com.example.filmfeedapp.ui.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
): ViewModel() {

    private val _detailsUiState: MutableStateFlow<UiState<MovieDetail>> = MutableStateFlow(UiState.Empty)
    val detailsUiState = _detailsUiState.asStateFlow()


    fun getMovieDetailsById(movieId: Int){
        viewModelScope.launch {
            UiState.safeCall{ getMovieDetailsUseCase(movieId) }.collect{
                _detailsUiState.emit(it)
            }
        }
    }

}