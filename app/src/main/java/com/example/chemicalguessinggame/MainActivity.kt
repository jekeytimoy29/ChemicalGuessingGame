package com.example.chemicalguessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.chemicalguessinggame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var chemicalsList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chemicalsList = mutableListOf<String>("Gold", "Zinc", "Magnesium", "Cobalt", "Lithium")
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun buttonGuessOnclick(view: View) {
        binding.editTextAddChemical.text.clear()
        val chemicalName = binding.editTextChemicalName.text.toString()

        if (chemicalName.isNotBlank()) {
            val randomChemical = chemicalsList.random()
            var message = getString(R.string.chemical_guessed_failed, randomChemical)
            var imageSource = when (randomChemical) {
                "Gold" -> R.drawable.gold
                "Magnesium" -> R.drawable.magnesium
                "Zinc" -> R.drawable.zinc
                "Cobalt" -> R.drawable.cobalt
                "Lithium" -> R.drawable.lithium
                else -> R.drawable.chemical
            }

            if (randomChemical.equals(chemicalName, true))
                message = getString(R.string.chemical_guessed_successfully, chemicalName)

            binding.textViewMessage.text = message
            binding.imageViewChemical.setImageResource(imageSource)
        } else {
            binding.textViewMessage.text = getString(R.string.enter_name_or_guess)
            binding.imageViewChemical.setImageResource(R.drawable.chemical)
            binding.editTextChemicalName.text.clear()
        }
    }

    fun buttonAddChemicalOnclick(view: View) {
        binding.editTextChemicalName.text.clear()
        val chemicalName = binding.editTextAddChemical.text.toString()

        if (chemicalName.isNotBlank()) {
            val containsChemical = chemicalsList.any {
                it.equals(chemicalName, true)
            }

            var message = getString(R.string.chemical_already_available, chemicalName)
            if (!containsChemical) {
                chemicalsList.add(chemicalName)
                message = getString(R.string.chemical_added_successfully, chemicalName)
            }
            binding.textViewMessage.text = message
        }
        else {
            binding.textViewMessage.text = getString(R.string.enter_name_or_guess)
            binding.editTextAddChemical.text.clear()
        }
    }


}