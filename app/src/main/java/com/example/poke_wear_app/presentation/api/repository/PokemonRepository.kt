package com.example.poke_wear_app.presentation.api.repository

import com.example.poke_wear_app.presentation.api.RetrofitClient
import com.example.poke_wear_app.presentation.api.model.Pokemon
import com.example.poke_wear_app.presentation.api.model.PokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository {
    suspend fun getPokemonList(): ResultWrapper<PokemonList> {
        return try {
            val response = withContext(Dispatchers.IO) {
                RetrofitClient.getApiService().getPokemonNameList().execute()
            }

            if (response.isSuccessful) {
                ResultWrapper.Success(response.body()!!)
            } else {
                ResultWrapper.Error("Failed to fetch data")
            }
        } catch (e: Exception) {
            ResultWrapper.Error("Network error: ${e.message}")
        }
    }

    suspend fun getPokemonDetails(pokemonID: Int): ResultWrapper<Pokemon> {
        return try {
            val response = withContext(Dispatchers.IO) {
                RetrofitClient.getApiService().getPokemonDetails(pokemonID + 1).execute()
            }

            if (response.isSuccessful) {
                ResultWrapper.Success(response.body()!!)
            } else {
                ResultWrapper.Error("Failed to fetch data")
            }
        } catch (e: Exception) {
            ResultWrapper.Error("Network error: ${e.message}")
        }
    }
}
