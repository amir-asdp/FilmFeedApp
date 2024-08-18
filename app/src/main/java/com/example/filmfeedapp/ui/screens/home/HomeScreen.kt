package com.example.filmfeedapp.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.data.model.common.MovieBrief
import com.example.filmfeedapp.ui.components.MovieItemHorizontalCard
import com.example.filmfeedapp.ui.theme.FilmFeedAppTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
){

    val homeUiState = viewModel.homeUiState.collectAsLazyPagingItems()
    val searchUiState = viewModel.searchUiState.collectAsLazyPagingItems()



}



@Composable
fun SearchBar(
    searchText: TextFieldValue,
    onSearchTextChange: (TextFieldValue) -> Unit,
    onClearClick: () -> Unit
) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        label = { Text("Search Movies") },
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
        singleLine = true
    )
}



@Composable
fun SearchMovieList(
    movies: List<MovieBrief>,
    onMovieClick: (MovieBrief) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(movies) { movie ->
            MovieItemHorizontalCard(
                movie = movie,
                onClick = { onMovieClick(movie) }
            )
        }
    }
}



@Preview
@Composable
fun HomeScreenPreview(){
    FilmFeedAppTheme{ HomeScreen(viewModel = viewModel()) }
}