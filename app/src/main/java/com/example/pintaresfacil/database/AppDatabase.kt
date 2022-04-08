package com.example.pintaresfacil.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pintaresfacil.database.dao.ClientDao
import com.example.pintaresfacil.database.dao.ItemDao
import com.example.pintaresfacil.database.dao.OrderDao
import com.example.pintaresfacil.database.typeConverters.OrderConverter
import com.example.pintaresfacil.models.Client
import com.example.pintaresfacil.models.Item
import com.example.pintaresfacil.models.Order


//Despues de definir las entidades y DAO solo falta definir la base de dadtos que los contendrá
@Database(entities = arrayOf(Client::class, Item::class, Order::class), version = 1)
@TypeConverters(OrderConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun clientDao(): ClientDao
    abstract fun itemDao(): ItemDao
    abstract fun orderDao(): OrderDao

}