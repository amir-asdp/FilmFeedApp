package com.example.filmfeedapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.filmfeedapp.ui.navigation.AppNavHost
import com.example.filmfeedapp.ui.theme.FilmFeedAppTheme
import com.example.filmfeedapp.ui.viewmodel.DetailsViewModel
import com.example.filmfeedapp.ui.viewmodel.HomeViewModel
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class MainActivity : ComponentActivity(), AndroidScopeComponent {

    override val scope: Scope by activityScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            FilmFeedAppTheme {
                AppNavHost(
                    homeViewModel = koinViewModel(),
                    detailsViewModel = koinViewModel()
                )
            }
        }
    }

}
