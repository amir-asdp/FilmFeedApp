package com.example.filmfeedapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.filmfeedapp.ui.screen.DetailsScreen
import com.example.filmfeedapp.ui.screen.HomeScreen
import com.example.filmfeedapp.ui.viewmodel.DetailsViewModel
import com.example.filmfeedapp.ui.viewmodel.HomeViewModel


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppNavRoute.Home.name,
    homeViewModel: HomeViewModel,
    detailsViewModel: DetailsViewModel
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable(
            route = AppNavRoute.Home.name
        ) {
            HomeScreen(navController, homeViewModel)
        }

        composable(
            route = "${AppNavRoute.Details.name}/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) {
            DetailsScreen(it.arguments!!.getInt("movieId"), navController, detailsViewModel)
        }
    }
}