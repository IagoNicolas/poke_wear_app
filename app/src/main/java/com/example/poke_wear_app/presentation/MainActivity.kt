package com.example.poke_wear_app.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonBorder
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.InlineSlider
import androidx.wear.compose.material.InlineSliderDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.SwipeToDismissBox
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.rememberSwipeToDismissBoxState
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.poke_wear_app.R
import com.example.poke_wear_app.presentation.api.model.PokemonListInfo
import com.example.poke_wear_app.presentation.api.repository.ResultWrapper
import com.example.poke_wear_app.presentation.viewmodel.PokemonViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    val navController = rememberNavController()
    val pokemonViewModel: PokemonViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "pokemonList"
    ) {
        composable("pokemonList") {
            PokemonListScreen(viewModel = pokemonViewModel, navController = navController)
        }
        composable("helloWorld/{index}") {
            val pokeNumber = it.arguments?.getString("index")?.replace(Regex("[{}]"), "")?.toInt()
            PokemonDetailsScreen(
                viewModel = pokemonViewModel,
                index = pokeNumber
            ) { navController.popBackStack() }
        }
    }
}

@Composable
fun PokemonListScreen(viewModel: PokemonViewModel, navController: NavController) {
    val pokemonListState by viewModel.pokemonList.observeAsState()
    viewModel.requestPokemonList()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        when (val result = pokemonListState) {
            is ResultWrapper.Success -> {
                PokemonList(
                    viewModel = viewModel,
                    navController = navController,
                    pokemonList = result.data.results
                )
            }

            is ResultWrapper.Error -> {
                // Handle the error case
            }

            else -> {}
        }
    }
}

@Composable
fun PokemonList(
    viewModel: PokemonViewModel,
    navController: NavController,
    pokemonList: List<PokemonListInfo> = emptyList()
) {
    val listState = rememberScalingLazyListState()

    Scaffold(
        timeText = { TimeText() },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
        positionIndicator = { PositionIndicator(scalingLazyListState = listState) }
    ) {
        val coroutineScope = rememberCoroutineScope()
        val focusRequester = remember { FocusRequester() }

        ScalingLazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .onRotaryScrollEvent {
                    coroutineScope.launch {
                        listState.animateScrollBy(it.verticalScrollPixels * 5)
                    }
                    true
                }
                .focusRequester(focusRequester)
                .focusable(),
            contentPadding = PaddingValues(
                top = 4.dp,
                start = 4.dp,
                end = 4.dp,
                bottom = 10.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
        ) {
            items(pokemonList.size) { index ->
                Chip(
                    modifier = Modifier.width(180.dp),
                    icon = {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${index + 1}.png")
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colors.onPrimary,
                            text = "${viewModel.extractNumberFromUrl(pokemonList[index].url)}: ${pokemonList[index].name}"
                        )
                    },
                    onClick = { viewModel.goToDetails(navController, index) }
                )
            }
        }
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}

@Composable
fun PokemonDetailsScreen(viewModel: PokemonViewModel, index: Int?, onDismissed: () -> Unit) {
    val state = rememberSwipeToDismissBoxState()
    val scrollState = rememberScrollState()

    Scaffold(
        timeText = { TimeText() },
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
                        .padding(bottom = 68.dp)
                        .padding(horizontal = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    AsyncImage(
                        model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${
                            index?.plus(
                                1
                            )
                        }.png",
                        modifier = Modifier
                            .padding(top = 48.dp)
                            .width(128.dp)
                            .height(128.dp),
                        contentDescription = null,
                    )
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_arrow_down_24),
                            contentDescription = null
                        )
                    }
                    SliderExample()
                    SliderExample()
                    SliderExample()
                    SliderExample()
                    SliderExample()
                    SliderExample()
                }
            }
        }
    }
}

@Composable
fun SliderExample() {
    var value by remember { mutableFloatStateOf(4f) }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        InlineSlider(
            value = value,
            onValueChange = { value = it },
            increaseIcon = { Icon(InlineSliderDefaults.Increase, null) },
            decreaseIcon = { Icon(InlineSliderDefaults.Decrease, null) },
            enabled = false,
            valueRange = 3f..6f,
            steps = 5,
            segmented = true,
            modifier = Modifier.height(16.dp)
        )
    }
}