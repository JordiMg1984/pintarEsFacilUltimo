package com.example.pintaresfacil.database.dao

import androidx.room.*
import com.example.pintaresfacil.models.Client
import com.example.pintaresfacil.models.Item
import com.example.pintaresfacil.models.Order


//Con en Dao nos comunicamos con la BD
@Dao
interface OrderDao {

    @Query("SELECT * FROM `Order` where client_mail = :email")
    suspend fun getAll(email: String): List<Order>

    @Insert
    suspend fun insert(order: Order)

    @Update
    suspend fun update(order: Order)

    @Delete
    suspend fun delete(order: Order)

}