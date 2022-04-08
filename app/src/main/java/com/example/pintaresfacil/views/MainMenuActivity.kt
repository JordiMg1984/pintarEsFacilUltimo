package com.example.pintaresfacil.views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.pintaresfacil.Constants
import com.example.pintaresfacil.databinding.ActivityMainMenuBinding
import com.example.pintaresfacil.models.Client
import com.example.pintaresfacil.models.OrderItem
import com.google.gson.Gson


//Cambiarlo a home activity


class MainMenuActivity : Activity() {

    private lateinit var binding: ActivityMainMenuBinding

    private var email: String? = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPref = this@MainMenuActivity.getSharedPreferences(
            Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE
        )

        email = sharedPref.getString(Constants.EMAIL, "")

        binding.nombreCliente.text = email

        initListeners()

    }

    private fun initListeners() {

        //Enviamos el mail a Items por si haciera falta
        binding.btnMakeOrder.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            intent.putExtra("email", email);
            startActivity(intent)
        }

        binding.btnPallet.setOnClickListener {
            val intent = Intent(this, PalletActivity::class.java)
            startActivity(intent)
        }

        binding.btnVideos.setOnClickListener {
            val intent = Intent(this, VideosActivity::class.java)
            startActivity(intent)
        }

        binding.btnShoppingCart.setOnClickListener {
            val intent = Intent(this, OrdersActivity::class.java)
            intent.putExtra("email", email);
            startActivity(intent)
        }
    }
}