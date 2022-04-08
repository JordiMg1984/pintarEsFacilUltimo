package com.example.pintaresfacil.database.typeConverters

import androidx.room.TypeConverter
import com.example.pintaresfacil.models.Item
import com.example.pintaresfacil.models.OrderItem
import com.google.gson.Gson

object OrderConverter {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun toOrder(data: String?): MutableList<OrderItem> {
        return if (data.isNullOrEmpty()) {
            mutableListOf()
        } else {
            gson.fromJson(data, Array<OrderItem>::class.java).map { it }
                .toMutableList()
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromItem(data: MutableList<OrderItem>?): String {
        return if (data == null) {
            ""
        } else {
            gson.toJson(data)
        }
    }
}
