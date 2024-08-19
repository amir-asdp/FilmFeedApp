package com.example.filmfeedapp.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.filmfeedapp.ui.component.Loading
import com.example.filmfeedapp.ui.model.BaseUiState
import com.example.filmfeedapp.ui.viewmodel.DetailsViewModel
import es.dmoral.toasty.Toasty

@Composable
fun DetailsScreen(
    movieId: Int,
    navController: NavHostController,
    detailsViewModel: DetailsViewModel
){

    detailsViewModel.getMovieDetailsById(movieId)
    val detailsUiState by detailsViewModel.detailsUiState.collectAsState()

    when(detailsUiState){
        is BaseUiState.IsLoading -> {
            Loading()
        }

        is BaseUiState.Success -> {

        }

        is BaseUiState.Error -> {
            Toasty.error(
                LocalContext.current,
                (detailsUiState as BaseUiState.Error).errorMessage
            ).show()
            navController.popBackStack()
        }
    }

    BackHandler {
        navController.popBackStack()
    }

}



