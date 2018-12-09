package com.daftmobile.a4bhomework5

interface PokemonDataSource {
    fun fetch(pokedexIndex: String, onSuccess: (PokemonItem) -> Unit, onError: (String) -> Unit)
}