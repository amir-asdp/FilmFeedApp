package com.example.filmfeedapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.data.model.common.MovieBrief
import com.example.filmfeedapp.ui.component.ErrorNextPageItem
import com.example.filmfeedapp.ui.component.Loading
import com.example.filmfeedapp.ui.component.MovieItemGridCard
import com.example.filmfeedapp.ui.component.MovieItemHorizontalCard
import com.example.filmfeedapp.ui.model.HomeUiState
import com.example.filmfeedapp.ui.viewmodel.HomeViewModel
import es.dmoral.toasty.Toasty

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel
){

    val homeUiState by homeViewModel.homeUiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBar(
            label = "Search Movies",
            searchText = homeUiState.searchText,
            onSearchTextChange = { homeViewModel.searchMovies(it) },
            onClearClick = { homeViewModel.searchMovies("") }
        )

        when(homeUiState){
            is HomeUiState.OnHomeView -> {
                HomeTopRatedMovieList(
                    movies = (homeUiState as HomeUiState.OnHomeView).moviesOrderByRatePagingItems.collectAsLazyPagingItems(),
                    onMovieClick = {navController.navigate("details/${it.id}")}
                )
            }

            is HomeUiState.OnSearchView -> {
                SearchMovieList(
                    movies = (homeUiState as HomeUiState.OnSearchView).searchPagingItems.collectAsLazyPagingItems(),
                    onMovieClick = {navController.navigate("details/${it.id}")}
                )
            }
        }

    }


}



@Composable
fun SearchBar(
    label: String,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onClearClick: () -> Unit
) {
    OutlinedTextField(
        label = { Text(label) },
        value = searchText,
        onValueChange = onSearchTextChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = onClearClick) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear Icon"
                )
            }
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(100)
    )
}

@Composable
fun SearchMovieList(
    movies: LazyPagingItems<MovieBrief>,
    onMovieClick: (MovieBrief) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(movies.itemCount) {
            MovieItemHorizontalCard(
                movie = movies[it]!!,
                onClick = { onMovieClick(movies[it]!!) }
            )
        }

        movies.apply {
            when{
                loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                    item {
                        Loading()
                    }
                }
                loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                    item {
                        Toasty.error(LocalContext.current, "Error").show()
                        ErrorNextPageItem { retry() }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeTopRatedMovieList(
    movies: LazyPagingItems<MovieBrief>,
    onMovieClick: (MovieBrief) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2)
    ) {
        items(movies.itemCount) {
            MovieItemGridCard(
                movie = movies[it]!!,
                onClick = { onMovieClick(movies[it]!!) }
            )
        }

        movies.apply {
            when{
                loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                    item {
                        Loading()
                    }
                }
                loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                    item {
                        Toasty.error(LocalContext.current, "Error").show()
                        ErrorNextPageItem { retry() }
                    }
                }
            }
        }
    }
}