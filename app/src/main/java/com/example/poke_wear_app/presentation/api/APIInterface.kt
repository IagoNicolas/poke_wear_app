package com.example.poke_wear_app.presentation.api

import com.example.poke_wear_app.presentation.api.model.Pokemon
import com.example.poke_wear_app.presentation.api.model.PokemonList
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    // TODO: Check the return types of these endpoint requests!!!
    @GET("pokemon/")
    fun getList(): Call<PokemonList>

    @GET("ability/")
    fun getAbility(): Call<List<String>>

    @GET("characteristic/")
    fun getCharacteristic(): Call<List<String>>
}