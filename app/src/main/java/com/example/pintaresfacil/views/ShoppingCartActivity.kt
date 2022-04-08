package com.example.pintaresfacil.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.pintaresfacil.Constants
import com.example.pintaresfacil.R
import com.example.pintaresfacil.database.AppDatabase
import com.example.pintaresfacil.database.typeConverters.OrderConverter
import com.example.pintaresfacil.databinding.ActivityShoppingCartBinding
import com.example.pintaresfacil.models.Item
import com.example.pintaresfacil.models.Order
import com.example.pintaresfacil.models.OrderItem
import com.example.pintaresfacil.models.toOrder
import com.example.pintaresfacil.views.adapters.ShopAdapter
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoppingCartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingCartBinding

    private val shopAdapter by lazy { ShopAdapter() }

    private var shoppingList = mutableListOf<OrderItem>()
    private var shoppingListItem = mutableListOf<Item>()
    private var email: String? = String()


    private var roomDatabase: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
/**
        //Recibimos los datos del ItemsActivity
        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("shop list")
            shoppingListItem = Gson().fromJson(value, Array<Item>::class.java).map { it }
                .toMutableList()
        }

        val sharedPref = this@ShoppingCartActivity.getSharedPreferences(
            Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE
        )
**/
        initDatabase()
        initView()
        initListeners()
        initSpinner()
       // returnUbication()
    }

    private fun returnUbication() {
        //Recibimos los datos del ItemsActivity
        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("Ubication")
            shopAdapter.collection = Gson().fromJson(value, Array<Item>::class.java).map { it }
                .toMutableList()
        }
    }

    private fun initSpinner() {
        //Definimos los campos del spinner
        val spinner = findViewById(R.id.spinner) as Spinner
        val adaptador: ArrayAdapter<*> = ArrayAdapter.createFromResource(
            this, R.array.opciones, android.R.layout.simple_spinner_item
        )
        adaptador.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinner.adapter = adaptador
    }

    private fun initDatabase() {
        roomDatabase =
            Room.databaseBuilder(this, AppDatabase::class.java, Constants.DATABASE_NAME).build()
    }

    private fun initView() {

        binding.rvItems.apply {
            layoutManager = LinearLayoutManager(this@ShoppingCartActivity)
            adapter = shopAdapter
        }
        //Guardamos en el adapter la lista shoppingList creada antes
        shopAdapter.collection = shoppingListItem
    }

    private fun initListeners() {
        shopAdapter.itemListener = {
            val list = shopAdapter.collection.toMutableList()
            list.remove(it)
            shopAdapter.collection = list
        }
        //Btn finalizar compra
        binding.btnFinishShopping.setOnClickListener {
            finishShopping()
        }
        //Btn borrar pedido
        binding.btnClearShoppingCart.setOnClickListener {
            shopAdapter.collection = mutableListOf()
        }
        //Btn volver menu principal
        binding.btnReturn.setOnClickListener {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }

        //Btn Abrir ubicación
        binding.btnUbication.setOnClickListener {
            val intent = Intent(this, LocationClient::class.java)
            startActivity(intent);
        }


    }

    //Al finalizar guardamos el pedido dentro de la Bd de pedidos "Order"
    private fun finishShopping() {
        GlobalScope.launch(Dispatchers.IO) {
            val orderDAO = roomDatabase?.orderDao()
            //email?:"" el ?: es para decir que si es null ponga un ""
            orderDAO?.insert(
                Order(client_mail = email ?: "", items = shopAdapter.collection.map { item -> item.toOrder() }
                    .toMutableList())
            )
            //Cojemos el stock de la Bd y lo restamos cada vez que cojamos uno
            val itemDAO = roomDatabase?.itemDao()
            shopAdapter.collection.forEach {
                --it.available
                itemDAO?.update(
                    it
                )
            }




            val intent = Intent(this@ShoppingCartActivity, MainMenuActivity::class.java)
            startActivity(intent)
        }
    }


}
