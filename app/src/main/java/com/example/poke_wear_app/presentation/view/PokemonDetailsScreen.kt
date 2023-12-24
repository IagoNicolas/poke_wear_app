package com.example.poke_wear_app.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.CurvedTextStyle
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.SwipeToDismissBox
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.TimeTextDefaults
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.curvedText
import androidx.wear.compose.material.rememberSwipeToDismissBoxState
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import com.example.poke_wear_app.R
import com.example.poke_wear_app.presentation.api.repository.ResultWrapper
import com.example.poke_wear_app.presentation.viewmodel.PokemonViewModel
import com.example.poke_wear_app.presentation.widget.AttributeSlider

@Composable
fun PokemonDetailsScreen(viewModel: PokemonViewModel, index: Int?, onDismissed: () -> Unit) {
    val leadingTextStyle = TimeTextDefaults.timeTextStyle(color = MaterialTheme.colors.primary)
    val pokemonDetailsState by viewModel.pokemonDetails.observeAsState()
    val state = rememberSwipeToDismissBoxState()
    val scrollState = rememberScrollState()

    viewModel.requestPokemonDetails(index)
    when (val result = pokemonDetailsState) {
        is ResultWrapper.Success -> {
            Scaffold(
                timeText = {
                    TimeText(
                        startLinearContent = {
                            Text(
                                text = result.data.name,
                                style = leadingTextStyle
                            )
                        },
                        startCurvedContent = {
                            curvedText(
                                text = result.data.name,
                                style = CurvedTextStyle(leadingTextStyle)
                            )
                        },
                    )
                },
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
                                .padding(bottom = 48.dp)
                                .padding(horizontal = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            AsyncImage(
                                model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/${
                                    index?.plus(
                                        1
                                    )
                                }.gif",
                                modifier = Modifier
                                    .padding(top = 48.dp, bottom = 32.dp)
                                    .scale(2f),
                                contentDescription = null,
                                imageLoader = ImageLoader.Builder(LocalContext.current).components {
                                    add(ImageDecoderDecoder.Factory())
                                }.build()
                            )
                            AttributeSlider(
                                result.data.stats[0].baseStat.toFloat() / 51,
                                R.drawable.hp_24,
                                2
                            )
                            AttributeSlider(
                                result.data.stats[1].baseStat.toFloat() / 36,
                                R.drawable.attack_24,
                                2
                            )
                            AttributeSlider(
                                result.data.stats[2].baseStat.toFloat() / 40,
                                R.drawable.shield_24,
                                2
                            )
                            AttributeSlider(
                                result.data.stats[3].baseStat.toFloat() / 36,
                                R.drawable.sp_attack_24,
                                2
                            )
                            AttributeSlider(
                                result.data.stats[4].baseStat.toFloat() / 40,
                                R.drawable.sp_shield_24,
                                2
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