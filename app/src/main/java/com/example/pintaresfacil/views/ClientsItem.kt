package com.example.pintaresfacil.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.pintaresfacil.Constants
import com.example.pintaresfacil.database.AppDatabase
import com.example.pintaresfacil.databinding.ActivityItemsBinding
import com.example.pintaresfacil.databinding.ClientsItemBinding
import com.example.pintaresfacil.models.Item
import com.example.pintaresfacil.views.adapters.ItemsAdapter
import com.example.pintaresfacil.views.adapters.ItemsClient
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ClientsItem : AppCompatActivity() {

    private lateinit var binding: ClientsItemBinding

    //Creo el adapter de forma by lazy - lo gestiona Android cuando mejor va
    private val itemsAdapter by lazy { ItemsClient() }


    private var roomDatabase: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ClientsItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPref = this@ClientsItem.getSharedPreferences(
            Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE
        )



        initDatabase()
        initView()
        initListeners()
    }

    private fun initDatabase() {
        roomDatabase =
            Room.databaseBuilder(this, AppDatabase::class.java, Constants.DATABASE_NAME).build()
    }

    //Falta acabar
    private fun initView() {

        binding.cvRoom.apply {
            layoutManager = LinearLayoutManager(this@ClientsItem)
            adapter = ItemsClient
        }

        getItems()
    }

    //Falta acabar
    private fun getItems() {
        GlobalScope.launch(Dispatchers.IO) {
            val clientDao = roomDatabase?.clientDao()
            //Hago la query y cojo todos los items y los meto en el adapter
            itemsAdapter.collection = clientDao?.getAll().orEmpty()
        }
    }

    private fun initListeners() {

        itemsAdapter.itemListener = { order ->
    }

}

