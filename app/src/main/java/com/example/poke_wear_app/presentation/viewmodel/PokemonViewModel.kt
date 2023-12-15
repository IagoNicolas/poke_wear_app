package com.example.poke_wear_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.poke_wear_app.presentation.api.RetrofitClient
import com.example.poke_wear_app.presentation.api.model.PokemonList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel : ViewModel() {
    fun requestPokemonList() {
        val apiInterface = RetrofitClient.getApiService()
        val call: Call<PokemonList> = apiInterface.getList()
        call.enqueue(
            object : Callback<PokemonList> {
                override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                    Log.e("A", response.body().toString())
                    when (response.code()) {
                        200 -> { return }
                        404 -> { return }
                        else -> { return }
                    }
                }

                override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                    call.cancel()
                }
            }
        )
    }
}
