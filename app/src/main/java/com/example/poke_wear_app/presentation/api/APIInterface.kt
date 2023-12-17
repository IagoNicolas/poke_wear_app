package com.example.poke_wear_app.presentation.api

import com.example.poke_wear_app.presentation.api.model.PokemonList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    // TODO: Check the return types of these endpoint requests!!!
    @GET("pokemon")
    fun getPokemonNameList(
        @Query("limit") limit: Int = 1017,
        @Query("offset") offset: Int = 0
    ): Call<PokemonList>

    @GET("ability/")
    fun getAbility(): Call<List<String>>

    @GET("characteristic/")
    fun getCharacteristic(): Call<List<String>>
}