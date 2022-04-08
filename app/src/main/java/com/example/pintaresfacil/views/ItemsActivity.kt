package com.example.pintaresfacil.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.pintaresfacil.Constants
import com.example.pintaresfacil.database.AppDatabase
import com.example.pintaresfacil.databinding.ActivityItemsBinding
import com.example.pintaresfacil.models.Item
import com.example.pintaresfacil.models.OrderItem
import com.example.pintaresfacil.models.toOrder
import com.example.pintaresfacil.views.adapters.ItemsAdapter
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemsActivity : AppCompatActivity() {

    private var email: String? = String()
    private lateinit var binding: ActivityItemsBinding

    //Creo el adapter de forma by lazy - lo gestiona Android cuando mejor va
    private val itemsAdapter by lazy { ItemsAdapter() }

    private var shoppingList = mutableListOf<Item>()
    private var roomDatabase: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPref = this@ItemsActivity.getSharedPreferences(
            Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE
        )

        email = sharedPref.getString(Constants.EMAIL, "")

        initDatabase()
        initView()
        initListeners()
    }

    private fun initDatabase() {
        roomDatabase =
            Room.databaseBuilder(this, AppDatabase::class.java, Constants.DATABASE_NAME).build()
    }

    private fun initView() {

        binding.rvItems.apply {
            layoutManager = LinearLayoutManager(this@ItemsActivity)
            adapter = itemsAdapter
        }

        getItems()
    }

    private fun getItems() {
        GlobalScope.launch(Dispatchers.IO) {
            val itemDao = roomDatabase?.itemDao()
            //Hago la query y cojo todos los items y los meto en el adapter
            itemsAdapter.collection = itemDao?.getAll().orEmpty()
        }
    }

    private fun initListeners() {

        //Cuando pulso el boton del carrito, filtro (filter) la lista por los item que esten con el check activado
        //Despues tasteo esa lista al objeto order item que es el que vamos a guardar en la BBDD haciendo un (map) que es igual que un bucle for
        binding.fabShopping.setOnClickListener {
            getShoppingList()
        }
    }

    private fun getShoppingList() {
        shoppingList = itemsAdapter.collection
            .filter { item -> item.checked }
            .toMutableList()

        val intent = Intent(this, ShoppingCartActivity::class.java)
        //Casteamo la shopping a gson para poder trasparla a string
        intent.putExtra("shop list", Gson().toJson(shoppingList))
        startActivity(intent)
    }
}
