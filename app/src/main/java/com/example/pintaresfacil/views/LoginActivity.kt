package com.example.pintaresfacil.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.room.Room
import com.example.pintaresfacil.Constants
import com.example.pintaresfacil.database.AppDatabase
import com.example.pintaresfacil.databinding.ActivityLoginBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    //Preparamos el bindbiding
    private lateinit var binding: ActivityLoginBinding

    //Preparamos la Bd
    private var roomDatabase: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
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
        binding.btnLogin.setOnClickListener {
            val clientDao = roomDatabase?.clientDao()
            //Creo un hilo en el IO que es el que recomienda ROOM
            GlobalScope.launch(Dispatchers.IO) {
                //Hago la query y checkeo si exitste el usuario con el email/password
                val login = clientDao?.login(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
                //si no me devuelve null es que el usuario existe, si es null muestro error
                if (login != null) {
                    val sharedPref = this@LoginActivity.getSharedPreferences(
                        Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE
                    )

                    sharedPref.edit().putString(Constants.EMAIL, binding.etEmail.text.toString()).apply()

                    val intent = Intent(this@LoginActivity, MainMenuActivity::class.java)
                    startActivity(intent)
                } else {
                    GlobalScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@LoginActivity,
                            "El cliente no existe",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
        binding.btnRegistry.setOnClickListener {
            val intent = Intent(this, RegistryActivity::class.java)
            startActivity(intent)
        }
    }

}