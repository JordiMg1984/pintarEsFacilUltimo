package com.example.pintaresfacil.views

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.pintaresfacil.databinding.ActivityPalletBinding

class PalletActivity : Activity() {

    private lateinit var binding: ActivityPalletBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPalletBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val arrayAdapter: ArrayAdapter<*>

        val valores = mutableListOf(
            "Carta colores para paredes interiores",
            "Carta colores interiores en tonos pasteles",
            "Carta de colores para madera/hierro en esmalte al agua satinado",
            "Carta colores paredes en exterior",
            "Carta de colores esmaltes al disolvente"
        )

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, valores)
        binding.listCartaColores.adapter = arrayAdapter

        binding.listCartaColores.setOnItemClickListener { _, _, position, _ ->

            when (position) {
                0 -> {
                    openURL("https://www.tollens.es/busqueda-de-color?collection=cromology-interior")
                }
                1 -> {
                    openURL("https://www.tollens.es/busqueda-de-color?collection=cromology-pasteles")
                }
                2 -> {
                    openURL("https://www.tollens.es/busqueda-de-color?collection=esmalte-agua-satinado")
                }
                3 -> {
                    openURL("https://reveton.com/carta-colores-proyecta-315-colores/")
                }
                4 -> {
                    openURL("https://reveton.com/web/wp-content/uploads/2020/11/carta-esmalte-sintetico-brillante.pdf")
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