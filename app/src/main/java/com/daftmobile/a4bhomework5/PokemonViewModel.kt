package com.daftmobile.a4bhomework5

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by Konrad Kowalewski. Edited by Alexander Repnikov.
 */
class PokemonViewModel: ViewModel() {
    private val pokemonDataSource: PokemonDataSource = PokemonFetcher()

    private val pokemonLiveData = SingleLiveEvent<PokemonItem>()
    private val errorLiveData = SingleLiveEvent<String>()

    fun newPokemon(): LiveData<PokemonItem> = pokemonLiveData
    fun error(): LiveData<String> = errorLiveData

    fun showPokemonInfo(pokedexIndex: String) {
        fetchDataFromApi(pokedexIndex)
    }

    private fun fetchDataFromApi(pokedexIndex: String) {
        pokemonDataSource.fetch(
            pokedexIndex,
            {
                errorLiveData.value = it
            }, {
                errorLiveData.value = it
            }
        )
    }

}
