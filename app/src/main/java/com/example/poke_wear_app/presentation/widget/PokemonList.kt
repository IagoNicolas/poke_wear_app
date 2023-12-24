package com.example.poke_wear_app.presentation.widget

import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
import coil.request.ImageRequest
import com.example.poke_wear_app.presentation.api.model.PokemonListInfo
import com.example.poke_wear_app.presentation.viewmodel.PokemonViewModel
import kotlinx.coroutines.launch


@Composable
fun PokemonList(
    viewModel: PokemonViewModel,
    navController: NavController,
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
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${index + 1}.png")
                                .crossfade(true)
                                .build(),
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
                    onClick = { viewModel.goToDetails(navController, index) }
                )
            }
        }
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}