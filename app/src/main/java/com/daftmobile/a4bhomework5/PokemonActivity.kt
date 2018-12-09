package com.daftmobile.a4bhomework5

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pokemon.*

class PokemonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        val pokemon = intent.getSerializableExtra("pokemon") as PokemonItem?

        if (pokemon == null) {
            nameView.text = "ERROR: No Pokemon..."
        } else {
            colorView.setBackgroundColor(pokemon.color)
            nameView.text = pokemon.name
            numberView.text = pokemon.number
        }
    }
}
