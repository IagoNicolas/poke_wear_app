package com.example.poke_wear_app.presentation

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.poke_wear_app.R
import com.example.poke_wear_app.presentation.api.model.PokemonListInfo
import com.example.poke_wear_app.presentation.api.repository.ResultWrapper
import com.example.poke_wear_app.presentation.theme.Poke_wear_appTheme
import com.example.poke_wear_app.presentation.viewmodel.PokemonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    val pokemonViewModel: PokemonViewModel = viewModel()
    val pokemonListState by pokemonViewModel.pokemonList.observeAsState()

    pokemonViewModel.requestPokemonList()

    Poke_wear_appTheme {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
        ) {
            when (val result = pokemonListState) {
                is ResultWrapper.Success -> {
                    // Pass the actual list of Pokemon names to PokemonList
                    PokemonList(viewModel = pokemonViewModel, pokemonList = result.data.results)
                }

                is ResultWrapper.Error -> {
                    // Handle the error case
                }

                else -> {}
            }
        }
    }
}

@Composable
fun Pokemon(pokemonId: Int, pokemonName: String) {
    val context = LocalContext.current
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(true, onClick = { showToast(context = context) })
            .padding(horizontal = 0.dp, vertical = 4.dp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = stringResource(R.string.pokemon_home_list_item, pokemonId, pokemonName)
    )
}

@Composable
fun PokemonList(
    viewModel: PokemonViewModel,
    modifier: Modifier = Modifier,
    pokemonList: List<PokemonListInfo> = emptyList()
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = pokemonList) { pokemon ->
            Pokemon(pokemonId = viewModel.extractNumberFromUrl(pokemon.url), pokemon.name)
        }
    }
}

fun showToast(context: Context) {
    Toast.makeText(context, MainActivity::class.simpleName, Toast.LENGTH_SHORT).show()
}