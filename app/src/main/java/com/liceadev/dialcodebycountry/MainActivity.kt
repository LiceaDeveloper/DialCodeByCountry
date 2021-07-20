package com.liceadev.dialcodebycountry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.liceadev.dialcodebycountry.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val index = binding.spCountry.countriesList.indexOfFirst { it.dialCode == "+52" }
        binding.spCountry.setSelection(index)


        binding.spCountry.onItemSelected {
            binding.textView.text = it.name
        }
    }
}
