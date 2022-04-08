package com.example.pintaresfacil.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.pintaresfacil.Constants
import com.example.pintaresfacil.database.AppDatabase
import com.example.pintaresfacil.databinding.ActivityRegistryBinding
import com.example.pintaresfacil.models.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegistryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistryBinding

    private var roomDatabase: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initDatabase()
        initListeners()
    }

    private fun initDatabase() {
        roomDatabase =
            Room.databaseBuilder(this, AppDatabase::class.java, Constants.DATABASE_NAME).build()
    }

    private fun initListeners() {
        binding.btnRegistry.setOnClickListener {
            //Control error mail if (y)~&&







            val clientDao = roomDatabase?.clientDao()
            GlobalScope.launch(Dispatchers.IO) {
                //El runCatching es un try catch.
                runCatching {
                    clientDao?.insert(
                        Client(
                            email = binding.etEmail.text.toString(),
                            name = binding.etName.text.toString(),
                            surName = binding.etSurname.text.toString(),
                            dni = binding.etDNI.text.toString(),
                            mobile = binding.etMobile.text.toString().toInt(),
                            password = binding.etPassword.text.toString()
                        )
                    )
                }.map {
                    //Si ha ido bien navego al main menu
                    val intent = Intent(this@RegistryActivity, MainMenuActivity::class.java)
                    startActivity(intent)
                }.getOrElse {
                    //Si da error, lo muestro
                    GlobalScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@RegistryActivity,
                            "No se ha podido registrar",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
            }
        }
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}