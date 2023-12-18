package com.example.poke_wear_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import coil.compose.AsyncImage
import com.example.poke_wear_app.presentation.api.model.PokemonListInfo
import com.example.poke_wear_app.presentation.api.repository.ResultWrapper
import com.example.poke_wear_app.presentation.theme.Poke_wear_appTheme
import com.example.poke_wear_app.presentation.viewmodel.PokemonViewModel
import kotlinx.coroutines.launch

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
    Poke_wear_appTheme {
        PokemonListScreen(pokemonViewModel)
    }
}

@Composable
fun PokemonListScreen(pokemonViewModel: PokemonViewModel) {
    pokemonViewModel.requestPokemonList()
    val pokemonListState by pokemonViewModel.pokemonList.observeAsState()

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

@Composable
fun PokemonList(
    viewModel: PokemonViewModel,
    pokemonList: List<PokemonListInfo> = emptyList()
) {
    val listState = rememberScalingLazyListState()

    Scaffold(
        timeText = { TimeText() },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
        positionIndicator = { PositionIndicator(scalingLazyListState = listState) }
    ) {
        val coroutineScope = rememberCoroutineScope()
        val focusRequester = remember { FocusRequester() }

        ScalingLazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .onRotaryScrollEvent {
                    coroutineScope.launch {
                        listState.animateScrollBy(it.verticalScrollPixels * 5)
                    }
                    true
                }
                .focusRequester(focusRequester)
                .focusable(),
            contentPadding = PaddingValues(
                top = 4.dp,
                start = 4.dp,
                end = 4.dp,
                bottom = 10.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
        ) {
            items(pokemonList.size) { index ->
                Chip(
                    modifier = Modifier.width(180.dp),
                    icon = {
                        AsyncImage(
                            model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${index + 1}.png",
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colors.onPrimary,
                            text = "${viewModel.extractNumberFromUrl(pokemonList[index].url)}: ${pokemonList[index].name}"
                        )
                    },
                    onClick = {}
                )
            }
        }
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}