package com.example.poke_wear_app.presentation.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.InlineSlider

@Composable
fun AttributeSlider(attribute: Float, drawableId: Int, padding: Int) {
    val value by remember { mutableFloatStateOf(attribute) }

    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = padding.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        InlineSlider(
            value = value,
            onValueChange = {  },
            increaseIcon = {
                Icon(
                    painter = painterResource(id = drawableId),
                    contentDescription = null
                )
            },
            decreaseIcon = {
                Icon(
                    painter = painterResource(id = drawableId),
                    contentDescription = null,
                    modifier = Modifier.scale(0.65f)
                )
            },
            //enabled = false,
            valueRange = 0f..5f,
            steps = 4,
            segmented = true,
            modifier = Modifier.height(16.dp)
        )
    }
}