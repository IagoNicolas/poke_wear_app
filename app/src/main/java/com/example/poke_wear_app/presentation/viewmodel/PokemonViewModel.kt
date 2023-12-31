package com.example.poke_wear_app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.poke_wear_app.presentation.api.model.Pokemon
import com.example.poke_wear_app.presentation.api.model.PokemonList
import com.example.poke_wear_app.presentation.api.repository.PokemonRepository
import com.example.poke_wear_app.presentation.api.repository.ResultWrapper
import kotlinx.coroutines.launch
import java.io.IOException

class PokemonViewModel : ViewModel() {
    private val pokemonRepository: PokemonRepository = PokemonRepository()

    // LiveData to observe the Pokemon list
    private val _pokemonList = MutableLiveData<ResultWrapper<PokemonList>>()
    val pokemonList: LiveData<ResultWrapper<PokemonList>> get() = _pokemonList

    // LiveData to observe the Pokemon info
    private val _pokemonDetails = MutableLiveData<ResultWrapper<Pokemon>>()
    val pokemonDetails: LiveData<ResultWrapper<Pokemon>> get() = _pokemonDetails

    fun requestPokemonList() {
        viewModelScope.launch {
            try {
                val result = pokemonRepository.getPokemonList()
                _pokemonList.value = result
            } catch (e: IOException) {
                // Handle network exceptions, if any
            } catch (e: Exception) {
                // Handle exceptions, if any
            }
        }
    }

    fun requestPokemonDetails(pokemonId: Int?) {
        viewModelScope.launch {
            try {
                val result = pokemonRepository.getPokemonDetails(pokemonId!!)
                _pokemonDetails.value = result
            } catch (e: IOException) {
                // Handle network exceptions, if any
            } catch (e: Exception) {
                // Handle exceptions, if any
            }
        }
    }

    fun extractNumberFromUrl(url: String): Int {
        val regex = Regex("/pokemon/(\\d+)/")
        val matchResult = regex.find(url)
        return matchResult?.groupValues?.get(1)?.toInt() ?: 0
    }


    fun goToDetails(navController: NavController, index: Int) {
        navController.navigate("pokemonDetails/{$index}")
    }
}
