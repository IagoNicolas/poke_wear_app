package com.example.poke_wear_app.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.poke_wear_app.R
import com.example.poke_wear_app.presentation.theme.Poke_wear_appTheme
import com.example.poke_wear_app.presentation.viewmodel.HomeViewModel

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
    val homeViewModel: HomeViewModel = viewModel()
    val pokemonList by homeViewModel.pokemonList.collectAsState()

    if (pokemonList.isEmpty()) {
        homeViewModel.fetchPokemonList()
    }
    Poke_wear_appTheme {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
        ) {
            PokemonList()
        }
    }
}

@Composable
fun Pokemon(greetingName: String) {
    val context = LocalContext.current
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(true, onClick = { showToast(context = context) })
            .padding(horizontal = 0.dp, vertical = 4.dp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = stringResource(R.string.list_preamble, greetingName)
    )
}

@Composable
private fun PokemonList(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Pokemon(greetingName = name)
        }
    }
}

fun showToast(context: Context) {
    Toast.makeText(context, MainActivity::class.simpleName, Toast.LENGTH_SHORT).show()
}

