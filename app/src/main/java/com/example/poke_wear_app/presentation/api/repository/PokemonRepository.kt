package com.example.poke_wear_app.presentation.api.repository

import com.example.poke_wear_app.presentation.api.RetrofitClient
import com.example.poke_wear_app.presentation.api.model.PokemonList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository {
    fun getPokemonList(callback: (ResultWrapper<PokemonList>) -> Unit) {
        val apiInterface = RetrofitClient.getApiService()
        val call: Call<PokemonList> = apiInterface.getList()

        call.enqueue(object : Callback<PokemonList> {
            override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                when {
                    response.isSuccessful -> callback(ResultWrapper.Success(response.body()!!))
                    else -> callback(ResultWrapper.Error("Failed to fetch data"))
                }
            }

            override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                callback(ResultWrapper.Error("Network error: ${t.message}"))
            }
        })
    }
}