package com.example.poke_wear_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.poke_wear_app.presentation.api.repository.PokemonRepository
import com.example.poke_wear_app.presentation.api.repository.ResultWrapper

class PokemonViewModel : ViewModel() {
    val repository: PokemonRepository = PokemonRepository()
    fun requestPokemonList() {
        repository.getPokemonList { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    val pokemonList = result.data
                    Log.e("A", pokemonList.toString())
                    // Handle the successful response
                }
                is ResultWrapper.Error -> {
                    Log.e("A", "Error: ${result.message}")
                    // Handle the error
                }
            }
        }
    }
}
