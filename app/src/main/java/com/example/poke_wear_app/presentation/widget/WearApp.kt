package com.example.poke_wear_app.presentation.widget

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.poke_wear_app.presentation.view.PokemonDetailsScreen
import com.example.poke_wear_app.presentation.view.PokemonListScreen
import com.example.poke_wear_app.presentation.viewmodel.PokemonViewModel

@Composable
fun WearApp() {
    val navController = rememberNavController()
    val pokemonViewModel: PokemonViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "pokemonList"
    ) {
        composable("pokemonList") {
            PokemonListScreen(viewModel = pokemonViewModel, navController = navController)
        }
        composable("helloWorld/{index}") {
            val pokeNumber = it.arguments?.getString("index")?.replace(Regex("[{}]"), "")?.toInt()
            PokemonDetailsScreen(
                viewModel = pokemonViewModel,
                index = pokeNumber
            ) { navController.popBackStack() }
        }
    }
}