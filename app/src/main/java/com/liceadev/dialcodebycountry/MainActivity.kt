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


        val index = binding.spi1.countriesList.indexOfFirst { it.dialCode == "+52" }
        binding.spi1.setSelection(index)


        binding.spi1.onItemSelected {
            binding.textView.text = it.name
        }
    }
}
