package com.example.poke_wear_app.presentation.api.repository

import com.example.poke_wear_app.presentation.api.APIInterface
import com.example.poke_wear_app.presentation.api.model.PokemonListInfo

class PokemonRepository(private val apiInterface: APIInterface) {
    suspend fun getPokemonList(): ResultWrapper<List<PokemonListInfo>> {
        return try {
            val response = apiInterface.getList().execute()

            if (response.isSuccessful) {
                ResultWrapper.Success(response.body()?.pokemonListInfos ?: emptyList())
            } else {
                ResultWrapper.Error("Error ${response.code()}: ${response.message()}")
            }
        } catch (e: Exception) {
            ResultWrapper.Error("Exception: ${e.message}")
        }
    }
}