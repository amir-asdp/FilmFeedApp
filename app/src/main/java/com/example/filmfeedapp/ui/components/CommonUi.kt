package com.example.filmfeedapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.model.common.MovieBrief
import com.example.filmfeedapp.R
import com.example.filmfeedapp.ui.theme.FilmFeedAppTheme

@Composable
fun MovieItemGridCard(
    movieBrief: MovieBrief,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){

    Card(
        modifier = modifier.padding(16.dp),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp)
        ) {

        Column(modifier = modifier.padding(16.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movieBrief.posterPhotoUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.test_movie_poster),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
            )
            Text(
                text = movieBrief.title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(8.dp)
            )

        }
    }

}

@Preview
@Composable
private fun MovieItemGridCardPreview(){
    FilmFeedAppTheme{
        MovieItemGridCard(
            movieBrief = MovieBrief(
                "Preview",
                "Preview",
                "Preview"
            ),
            {}
        )
    }
}

@Composable
fun MovieItemHorizontalCard(
    movieBrief: MovieBrief,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){

    Card(
        modifier = modifier.padding(16.dp).fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp)
    ) {

        Row(modifier = modifier.padding(16.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movieBrief.posterPhotoUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.test_movie_poster),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
            )
            Text(
                text = movieBrief.title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(8.dp)
            )

        }
    }

}

@Preview
@Composable
private fun MovieItemHorizontalCardPreview(){
    FilmFeedAppTheme{
        MovieItemHorizontalCard(
            movieBrief = MovieBrief(
                "Preview",
                "Preview",
                "Preview"
            ),
            {}
        )
    }
}