package com.example.poke_wear_app.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.wear.compose.material.MaterialTheme
import com.example.poke_wear_app.presentation.api.repository.ResultWrapper
import com.example.poke_wear_app.presentation.viewmodel.PokemonViewModel
import com.example.poke_wear_app.presentation.widget.PokemonList

@Composable
fun PokemonListScreen(viewModel: PokemonViewModel, navController: NavController) {
    val pokemonListState by viewModel.pokemonList.observeAsState()
    viewModel.requestPokemonList()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        when (val result = pokemonListState) {
            is ResultWrapper.Success -> {
                PokemonList(
                    viewModel = viewModel,
                    navController = navController,
                    pokemonList = result.data.results
                )
            }

            is ResultWrapper.Error -> {
                // Handle the error case
            }

            else -> {}
        }
    }
}