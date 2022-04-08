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
import com.example.pintaresfacil.databinding.ActivityOrdersBinding
import com.example.pintaresfacil.models.Item
import com.example.pintaresfacil.models.OrderItem
import com.example.pintaresfacil.models.toOrder
import com.example.pintaresfacil.views.adapters.ItemsAdapter
import com.example.pintaresfacil.views.adapters.OrdersAdapter
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OrdersActivity : AppCompatActivity() {

    private var email: String? = String()
    private lateinit var binding: ActivityOrdersBinding

    //Creo el adapter de forma by lazy - lo gestiona Android cuando mejor va
    private val itemsAdapter by lazy { OrdersAdapter() }

    private var roomDatabase: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOrdersBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPref = this@OrdersActivity.getSharedPreferences(
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
            layoutManager = LinearLayoutManager(this@OrdersActivity)
            adapter = itemsAdapter
        }

        getItems()
    }

    private fun getItems() {
        GlobalScope.launch(Dispatchers.IO) {
            val itemDao = roomDatabase?.orderDao()
            //Hago la query y cojo todos los items y los meto en el adapter
            itemsAdapter.collection = itemDao?.getAll(email ?: "").orEmpty()
        }
    }

    private fun initListeners() {

        itemsAdapter.itemListener = { order ->

        }
    }
}
