package com.example.poke_wear_app.presentation.api

import com.example.poke_wear_app.presentation.api.model.Pokemon
import com.example.poke_wear_app.presentation.api.model.PokemonList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {
    @GET("pokemon")
    fun getPokemonNameList(
        @Query("limit") limit: Int = 1017,
        @Query("offset") offset: Int = 0
    ): Call<PokemonList>

    @GET("pokemon/{id}")
    fun getPokemonDetails(@Path("id") pokemonId: Int): Call<Pokemon>
}