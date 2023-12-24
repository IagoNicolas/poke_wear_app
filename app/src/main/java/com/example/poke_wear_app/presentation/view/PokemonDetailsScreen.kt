package com.example.poke_wear_app.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.SwipeToDismissBox
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.rememberSwipeToDismissBoxState
import coil.compose.AsyncImage
import com.example.poke_wear_app.R
import com.example.poke_wear_app.presentation.api.repository.ResultWrapper
import com.example.poke_wear_app.presentation.viewmodel.PokemonViewModel
import com.example.poke_wear_app.presentation.widget.AttributeSlider

@Composable
fun PokemonDetailsScreen(viewModel: PokemonViewModel, index: Int?, onDismissed: () -> Unit) {
    val pokemonDetailsState by viewModel.pokemonDetails.observeAsState()
    val state = rememberSwipeToDismissBoxState()
    val scrollState = rememberScrollState()

    viewModel.requestPokemonDetails(index)
    when (val result = pokemonDetailsState) {
        is ResultWrapper.Success -> {
            Scaffold(
                timeText = { TimeText() },
                vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
            ) {
                SwipeToDismissBox(
                    onDismissed = { onDismissed.invoke() },
                    state = state
                ) { isBackground ->
                    if (isBackground) {
                        Box(modifier = Modifier.fillMaxSize())
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(state = scrollState)
                                .padding(bottom = 52.dp)
                                .padding(horizontal = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            AsyncImage(
                                model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${
                                    index?.plus(
                                        1
                                    )
                                }.png",
                                modifier = Modifier
                                    .padding(top = 48.dp)
                                    .width(128.dp)
                                    .height(128.dp),
                                contentDescription = null,
                            )
                            Button(
                                onClick = { },
                                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.round_arrow_down_24),
                                    contentDescription = null
                                )
                            }
                            AttributeSlider(
                                result.data.stats[0].baseStat.toFloat() / 51,
                                R.drawable.hp_24,
                                4
                            )
                            AttributeSlider(
                                result.data.stats[1].baseStat.toFloat() / 36,
                                R.drawable.attack_24,
                                4
                            )
                            AttributeSlider(
                                result.data.stats[2].baseStat.toFloat() / 40,
                                R.drawable.shield_24,
                                4
                            )
                            AttributeSlider(
                                result.data.stats[3].baseStat.toFloat() / 36,
                                R.drawable.sp_attack_24,
                                4
                            )
                            AttributeSlider(
                                result.data.stats[4].baseStat.toFloat() / 40,
                                R.drawable.sp_shield_24,
                                4
                            )
                            AttributeSlider(
                                result.data.stats[5].baseStat.toFloat() / 40,
                                R.drawable.speed_24,
                                0
                            )
                        }
                    }
                }
            }
        }

        is ResultWrapper.Error -> {
            // Handle the error case
        }

        else -> {}
    }
}