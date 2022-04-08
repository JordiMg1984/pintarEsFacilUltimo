package com.example.pintaresfacil.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.room.Room
import com.example.pintaresfacil.Constants
import com.example.pintaresfacil.R
import com.example.pintaresfacil.database.AppDatabase
import com.example.pintaresfacil.databinding.ActivityLoginBinding
import com.example.pintaresfacil.databinding.ActivityMainBinding
import com.example.pintaresfacil.models.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    //Iniciamos el binding mas tarde
    private lateinit var binding: ActivityMainBinding

    //Instanciamos la Bd a null, para luedo hacerlo
    private var roomDatabase: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPref = this.getSharedPreferences(
            Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE
        )

        //Si es a 1a vez que etramos a la app vamos directamente a registrar, sino al login siempre
        if (sharedPref.getBoolean(Constants.FIRST_TIME, true)) {
            initDatabase()
            initItems()
            sharedPref.edit().putBoolean(Constants.FIRST_TIME, false).apply()
            val intent = Intent(this, RegistryActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initDatabase() {
        roomDatabase =
            Room.databaseBuilder(this, AppDatabase::class.java, Constants.DATABASE_NAME).build()
    }

    //Lanzamos una corrutina con globalscope para q dispare el hilo, cargamos por 1a vez la lista items
    private fun initItems() {
        GlobalScope.launch(Dispatchers.IO) {
            val itemDao = roomDatabase?.itemDao()
            itemDao?.insertAll(Item.items())

        }
    }
}