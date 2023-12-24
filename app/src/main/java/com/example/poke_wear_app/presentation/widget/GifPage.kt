package com.example.poke_wear_app.presentation.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder

@Composable
fun GifPage(index: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 36.dp),
    ) {
        AsyncImage(
            model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/${
                index.plus(
                    1
                )
            }.gif",
            modifier = Modifier
                .fillMaxSize()
                .scale(0.9f),
            contentDescription = null,
            imageLoader = ImageLoader.Builder(LocalContext.current).components {
                add(ImageDecoderDecoder.Factory())
            }.build()
        )
    }
}