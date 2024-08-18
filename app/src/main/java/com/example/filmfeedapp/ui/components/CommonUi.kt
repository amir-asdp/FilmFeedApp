package com.example.filmfeedapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    movie: MovieBrief,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){

    ElevatedCard(
        modifier = modifier.padding(16.dp),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp)
        ) {

        Column(modifier = modifier.padding(16.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterPhotoUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.twotone_movie_24),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
            )
            Text(
                text = movie.title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(8.dp)
            )

        }
    }

}

@Composable
fun MovieItemHorizontalCard(
    movie: MovieBrief,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){

    ElevatedCard(
        modifier = modifier.padding(16.dp).fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp)
    ) {

        Row(modifier = modifier.padding(16.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterPhotoUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.twotone_movie_24),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = movie.title,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            )

        }
    }

}



@Preview
@Composable
private fun MovieItemGridCardPreview(){
    FilmFeedAppTheme{
        MovieItemGridCard(
            movie = MovieBrief(
                "Preview",
                "Preview",
                "Preview"
            ),
            {}
        )
    }
}

@Preview
@Composable
private fun MovieItemHorizontalCardPreview(){
    FilmFeedAppTheme{
        MovieItemHorizontalCard(
            movie = MovieBrief(
                "Preview",
                "Preview",
                "Preview"
            ),
            {}
        )
    }
}