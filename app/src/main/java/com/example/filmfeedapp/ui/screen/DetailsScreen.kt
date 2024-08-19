package com.example.filmfeedapp.ui.screen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.model.common.MovieDetail
import com.example.filmfeedapp.R
import com.example.filmfeedapp.ui.component.Loading
import com.example.filmfeedapp.ui.model.BaseUiState
import com.example.filmfeedapp.ui.theme.FilmFeedAppTheme
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
            val movie = (detailsUiState as BaseUiState.Success).resultValue

            DetailsLayout(movie = movie)
        }

        is BaseUiState.Error -> {
            Log.e("DetailsScreen", (detailsUiState as BaseUiState.Error).errorMessage)
            Toasty.error(LocalContext.current, "Error").show()
            navController.popBackStack()
        }
    }

    BackHandler {
        navController.popBackStack()
    }

}



@Composable
fun DetailsLayout(movie: MovieDetail){
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.posterPhotoUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.twotone_movie_24),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(
                    text = "Genre:",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = Modifier.width(4.dp))
                movie.genres.forEach {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Release Date: ${movie.releaseDate}",
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "${movie.releaseDate}/10",
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Overview",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsLayoutPreview(){
    FilmFeedAppTheme {
        DetailsLayout(
            movie = MovieDetail(
                "test",
                "test",
                "test",
                "test",
                "test",
                "test",
                "test",
                "test",
                listOf("test1", "test2"),
                "test",
                "test",
                "test",
            )
        )
    }
}