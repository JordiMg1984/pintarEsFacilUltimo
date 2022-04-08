package com.example.pintaresfacil.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderItem(
    val name: String,
    val image_url: String,
    val price: Int,
)

fun Item.toOrder() = OrderItem(this.name, this.image_url, this.price)
