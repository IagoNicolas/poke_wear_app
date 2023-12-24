package com.example.poke_wear_app.presentation.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.CurvedTextStyle
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.TimeTextDefaults
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.curvedText
import com.example.poke_wear_app.presentation.viewmodel.PokemonViewModel

@Composable
fun ErrorIndicator(viewModel: PokemonViewModel) {
    val leadingTextStyle = TimeTextDefaults.timeTextStyle(color = Color.Red)

    Scaffold(
        timeText = {
            TimeText(
                startLinearContent = {
                    Text(
                        text = "Network error",
                        style = leadingTextStyle
                    )
                },
                startCurvedContent = {
                    curvedText(
                        text = "Network error",
                        style = CurvedTextStyle(leadingTextStyle)
                    )
                },
            )
        },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.width(96.dp).height(48.dp),
                onClick = { viewModel.requestPokemonList() }) {
                Text("Retry")
            }
        }
    }
}