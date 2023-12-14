package com.example.poke_wear_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poke_wear_app.presentation.api.RetrofitClient
import com.example.poke_wear_app.presentation.api.repository.PokemonRepository
import com.example.poke_wear_app.presentation.api.repository.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {
    private val repository = PokemonRepository(RetrofitClient.getApiService())

    // StateFlow to represent the Pokemon list
    private val _pokemonList = MutableStateFlow<List<String>>(emptyList())
    val pokemonList: StateFlow<List<String>> = _pokemonList

    fun fetchPokemonList() {
        viewModelScope.launch {
            try {
                // Fetch data from the repository
                val result = repository.getPokemonList()

                if (result is ResultWrapper.Success) {
                    // Update the state with the list of Pokemon names
                    _pokemonList.value = result.data.map { it.name }
                    Log.e(HomeViewModel::class.simpleName, "Success")
                } else if (result is ResultWrapper.Error) {
                    // Handle error if needed
                    Log.e(HomeViewModel::class.simpleName, "Error: " + result.message)
                }
            } catch (e: Exception) {
                // Handle exceptions if needed
                Log.e(HomeViewModel::class.simpleName, "Exception")
            }
        }
    }
}