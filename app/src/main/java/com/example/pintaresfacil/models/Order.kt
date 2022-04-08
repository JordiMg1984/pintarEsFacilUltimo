package com.example.pintaresfacil.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

//Tabla pedidos
@Entity
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val client_mail: String,
   // val Ubication: String,
    @TypeConverters
    val items: List<OrderItem>
)
