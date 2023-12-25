package com.example.poke_wear_app.presentation.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import com.example.poke_wear_app.presentation.utils.DetailsUtils

@Composable
fun TypeWidget(firstType: String, secondType: String? = null) {
    AsyncImage(
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 2.dp),
        model = DetailsUtils.getDrawable(firstType),
        imageLoader = ImageLoader.Builder(LocalContext.current).components {
            add(ImageDecoderDecoder.Factory())
        }.build(),
        contentDescription = null
    )
    secondType?.let {
        AsyncImage(
            modifier = Modifier
                .padding(horizontal = 6.dp, vertical = 2.dp),
            model = DetailsUtils.getDrawable(secondType),
            imageLoader = ImageLoader.Builder(LocalContext.current).components {
                add(ImageDecoderDecoder.Factory())
            }.build(),
            contentDescription = null
        )
    }
}