package com.example.poke_wear_app.presentation.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition

@Composable
fun LoadingScreen() {
    Scaffold(
    timeText = { TimeText() },
    vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize().padding(all = 96.dp),
        )
    }
}