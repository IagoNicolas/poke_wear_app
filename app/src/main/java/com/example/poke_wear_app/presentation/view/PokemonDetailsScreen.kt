package com.example.poke_wear_app.presentation.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.CurvedTextStyle
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.SwipeToDismissBox
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.TimeTextDefaults
import androidx.wear.compose.material.curvedText
import androidx.wear.compose.material.rememberSwipeToDismissBoxState
import com.example.poke_wear_app.presentation.api.repository.ResultWrapper
import com.example.poke_wear_app.presentation.viewmodel.PokemonViewModel
import com.example.poke_wear_app.presentation.widget.ErrorIndicator
import com.example.poke_wear_app.presentation.widget.GifPage
import com.example.poke_wear_app.presentation.widget.LoadingIndicator
import com.example.poke_wear_app.presentation.widget.StatsPage
import com.example.poke_wear_app.presentation.widget.TypeWidget

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonDetailsScreen(viewModel: PokemonViewModel, index: Int?, onDismissed: () -> Unit) {
    val leadingTextStyle = TimeTextDefaults.timeTextStyle(color = MaterialTheme.colors.primary)
    val pokemonDetailsState by viewModel.pokemonDetails.observeAsState()
    val state = rememberSwipeToDismissBoxState()

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
            ) {
                SwipeToDismissBox(
                    onDismissed = { onDismissed.invoke() },
                    state = state
                ) { isBackground ->
                    if (isBackground) {
                        Box(modifier = Modifier.fillMaxSize())
                    } else {
                        val pagerState = rememberPagerState(pageCount = { 2 })
                        VerticalPager(state = pagerState) { page ->
                            when (page) {
                                0 -> { index?.let { GifPage(index = it) } }
                                1 -> { StatsPage(result = result.data) }
                                2 -> { StatsPage(result = result.data) }
                                else -> { Text(text = "Page: $page", modifier = Modifier.fillMaxWidth()) }
                            }
                        }

                        Row(
                            Modifier
                                .wrapContentHeight()
                                .wrapContentWidth()
                                .rotate(90f)
                                .align(Alignment.CenterEnd),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            repeat(pagerState.pageCount) { iteration ->
                                val color = if (pagerState.currentPage == iteration) MaterialTheme.colors.primary else Color.LightGray
                                Box(
                                    modifier = Modifier
                                        .padding(horizontal = 2.dp)
                                        .clip(CircleShape)
                                        .background(color)
                                        .size(4.dp)
                                )
                            }
                        }

                        Column(modifier = Modifier.align(Alignment.CenterStart)) {
                            result.data.types.takeIf { it.isNotEmpty() }?.let {
                                TypeWidget(it[0].type.name, it.getOrNull(1)?.type?.name)
                            }
                        }
                    }
                }
            }
        }

        is ResultWrapper.Error -> { ErrorIndicator(viewModel) }

        else -> { LoadingIndicator() }
    }
}