package com.example.pintaresfacil.views

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.pintaresfacil.databinding.ActivityPalletBinding

class VideosActivity : Activity() {

    private lateinit var binding: ActivityPalletBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPalletBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val arrayAdapter: ArrayAdapter<*>

        val valores = mutableListOf(
            "Cómo pintar paredes en interior",
            "Pintar hierro en exteriores",
            "Trucos para pintar madera"
        )

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, valores)
        binding.listCartaColores.adapter = arrayAdapter

        binding.listCartaColores.setOnItemClickListener { _, _, position, _ ->

            when (position) {
                0 -> {
                    openURL("https://www.youtube.com/watch?v=RlYoZR_VY3Q/")
                }
                1 -> {
                    openURL("https://www.youtube.com/watch?v=ytA5Lo8SF-s")
                }
                2 -> {
                    openURL("https://www.youtube.com/watch?v=3lqo1Ju7joE")
                }
            }
        }
    }

    private fun openURL(url: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data =
            Uri.parse(url)
        startActivity(openURL)
    }
}