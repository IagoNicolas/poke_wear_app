package com.example.poke_wear_app.presentation.utils

import com.example.poke_wear_app.R

object DetailsUtils {
    fun getDrawable(typeName: String): Int {
        return when(typeName) {
            "normal" -> { R.drawable.normal }
            "fighting" -> { R.drawable.fighting }
            "flying" -> { R.drawable.flying }
            "poison" -> { R.drawable.poison }
            "ground" -> { R.drawable.ground }
            "rock" -> { R.drawable.rock }
            "bug" -> { R.drawable.bug }
            "ghost" -> { R.drawable.ghost }
            "steel" -> { R.drawable.steel }
            "fire" -> { R.drawable.fire }
            "water" -> { R.drawable.water }
            "grass" -> { R.drawable.grass }
            "electric" -> { R.drawable.electric }
            "psychic" -> { R.drawable.psychic }
            "ice" -> { R.drawable.ice }
            "dragon" -> { R.drawable.dragon }
            "dark" -> { R.drawable.dark }
            "fairy" -> { R.drawable.fairy }
            else -> { R.drawable.normal }
        }
    }
}