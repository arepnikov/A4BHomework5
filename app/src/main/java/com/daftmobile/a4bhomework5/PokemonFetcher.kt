package com.daftmobile.a4bhomework5

import android.provider.Settings
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class PokemonFetcher: PokemonDataSource {

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://switter.app.daftmobile.com/")
            .build()

    private val pokemonApi = retrofit.create(PokemonAPI::class.java)

    override fun fetch(pokedexIndex: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        val call = pokemonApi.peekPokemonInfo(pokedexIndex)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                onError(t.message ?: "No message")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    onSuccess(response.body()?.string() ?: "Weird empty response")
                } else {
                    onError("Serwer zwrocil: ${response.code()}")
                }
            }
        })
    }
}
