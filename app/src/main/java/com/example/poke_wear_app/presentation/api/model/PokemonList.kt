package com.example.poke_wear_app.presentation.api.model

data class PokemonList(
    val count: Long,
    val next: Any?,
    val previous: Any?,
    val results: List<PokemonListInfo>,
)

data class PokemonListInfo(
    val name: String,
    val url: String,
)
