package com.daftmobile.a4bhomework5

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {
    // /api/pokemon/:number/peek
    @GET("/api/pokemon/{pokedexIndex}/peek")
    fun peekPokemonInfo(@Path("pokedexIndex") pokedexIndex: String): Call<PokemonItem>

}