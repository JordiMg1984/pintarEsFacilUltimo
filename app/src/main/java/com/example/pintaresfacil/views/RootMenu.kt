package com.example.pintaresfacil.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pintaresfacil.databinding.ActivityItemsBinding
import com.example.pintaresfacil.databinding.ActivityMainMenuBinding
import com.example.pintaresfacil.databinding.ActivityRootMenuBinding

class RootMenu : AppCompatActivity() {

    private lateinit var binding: ActivityRootMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRootMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        //Definimos el btn para ver la lista clientes
        binding.btnListClient.setOnClickListener {
            val intent = Intent(this, PalletActivity::class.java)
            startActivity(intent)
        }

        //Definimos el btn para ver la lista clientes
        binding.btnListOrder.setOnClickListener {
            val intent = Intent(this, PalletActivity::class.java)
            startActivity(intent)
        }

        //Definimos el btn para ver la lista clientes
        binding.btnBackRoot.setOnClickListener {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }




    }




}