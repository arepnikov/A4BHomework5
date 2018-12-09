package com.daftmobile.a4bhomework5

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonFetcher: PokemonDataSource {

    private val client = OkHttpClient.Builder()
            .build()

    private val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://switter.app.daftmobile.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val pokemonApi = retrofit.create(PokemonAPI::class.java)

    override fun fetch(pokedexIndex: String, onSuccess: (PokemonItem) -> Unit, onError: (String) -> Unit) {
        val call = pokemonApi.peekPokemonInfo(pokedexIndex)

        call.enqueue(object : Callback<PokemonItem> {
            override fun onFailure(call: Call<PokemonItem>, t: Throwable) {
                onError(t.message ?: "No message")
            }

            override fun onResponse(call: Call<PokemonItem>, response: Response<PokemonItem>) {
                if (response.isSuccessful) {
                    val pokemonItem = response.body()
                    if (pokemonItem == null) {
                        onError("Pusta odpowiedz")
                    } else {
                        onSuccess(pokemonItem)
                    }

                } else {
                    onError("Serwer zwrocil: ${response.code()}")
                }
            }
        })
    }
}
