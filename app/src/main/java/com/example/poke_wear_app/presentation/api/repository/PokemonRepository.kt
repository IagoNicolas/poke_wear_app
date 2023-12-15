package com.example.poke_wear_app.presentation.api.repository

import com.example.poke_wear_app.presentation.api.RetrofitClient
import com.example.poke_wear_app.presentation.api.model.PokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository {
    suspend fun getPokemonList(): ResultWrapper<PokemonList> {
        return try {
            val response = withContext(Dispatchers.IO) {
                RetrofitClient.getApiService().getList().execute()
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
