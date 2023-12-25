package com.example.poke_wear_app.presentation.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.poke_wear_app.presentation.utils.DetailsUtils

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TypeWidget(firstType: String, secondType: String? = null) {
    GlideImage(
        modifier = Modifier
            .scale(1.5f)
            .padding(horizontal = 8.dp, vertical = 2.dp),
        model = DetailsUtils.getDrawable(firstType),
        contentDescription = null
    )
    secondType?.let {
        GlideImage(
            modifier = Modifier
                .scale(1.5f)
                .padding(horizontal = 8.dp, vertical = 2.dp),
            model = DetailsUtils.getDrawable(secondType),
            contentDescription = null
        )
    }
}