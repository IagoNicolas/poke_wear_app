package com.example.poke_wear_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poke_wear_app.presentation.api.repository.PokemonRepository
import com.example.poke_wear_app.presentation.api.repository.ResultWrapper
import kotlinx.coroutines.launch
import java.io.IOException

class PokemonViewModel : ViewModel() {
    private val pokemonRepository: PokemonRepository = PokemonRepository()

    fun requestPokemonList() {
        viewModelScope.launch {
            try {
                when (val result = pokemonRepository.getPokemonList()) {
                    is ResultWrapper.Success -> {
                        // Handle the success case
                        val pokemonList = result.data
                    }
                    is ResultWrapper.Error -> {
                        // Handle the error case
                    }
                }
            } catch (e: IOException) {
                // Handle network exceptions, if any
            } catch (e: Exception) {
                // Handle exceptions, if any
            }
        }
    }
}
