package com.example.poke_wear_app.presentation.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ToggleButton
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import com.example.poke_wear_app.R

@Composable
fun GifPage(index: Int) {
    var checked by remember { mutableStateOf(false) }
    var link by remember {
        mutableStateOf(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/${
                index.plus(
                    1
                )
            }.gif"
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 36.dp),
    ) {
        AsyncImage(
            model = link,
            modifier = Modifier
                .fillMaxSize()
                .scale(0.9f),
            contentDescription = null,
            imageLoader = ImageLoader.Builder(LocalContext.current).components {
                add(ImageDecoderDecoder.Factory())
            }.build()
        )

        ToggleButton(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 8.dp)
            .size(24.dp),
            checked = checked,
            onCheckedChange = {
                checked = it
                if (!checked) {
                    link = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/${
                        index.plus(
                            1
                        )
                    }.gif"
                } else {
                    link = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/shiny/${
                        index.plus(
                            1
                        )
                    }.gif"
                }
            }) {
            Icon(
                modifier = Modifier.padding(all = 4.dp),
                painter = painterResource(id = R.drawable.shiny_24),
                contentDescription = null
            )
        }
    }
}