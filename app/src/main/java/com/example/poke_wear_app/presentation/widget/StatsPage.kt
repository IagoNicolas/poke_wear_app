package com.example.poke_wear_app.presentation.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.poke_wear_app.R
import com.example.poke_wear_app.presentation.api.model.Pokemon

@Composable
fun StatsPage(result: Pokemon) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AttributeSlider(
                result.stats[0].baseStat.toFloat() / 51,
                R.drawable.hp_24,
                2
            )
            AttributeSlider(
                result.stats[1].baseStat.toFloat() / 36,
                R.drawable.attack_24,
                2
            )
            AttributeSlider(
                result.stats[2].baseStat.toFloat() / 40,
                R.drawable.shield_24,
                2
            )
            AttributeSlider(
                result.stats[3].baseStat.toFloat() / 36,
                R.drawable.sp_attack_24,
                2
            )
            AttributeSlider(
                result.stats[4].baseStat.toFloat() / 40,
                R.drawable.sp_shield_24,
                2
            )
            AttributeSlider(
                result.stats[5].baseStat.toFloat() / 40,
                R.drawable.speed_24,
                0
            )
        }
        AsyncImage(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter),
            model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-viii/icons/${
                result.id
            }.png", contentDescription = null
        )
    }
}