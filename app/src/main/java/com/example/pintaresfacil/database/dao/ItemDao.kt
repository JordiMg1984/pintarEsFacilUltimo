package com.example.pintaresfacil.database.dao

import androidx.room.*
import com.example.pintaresfacil.models.Client
import com.example.pintaresfacil.models.Item


//Con en Dao nos comunicamos con la BD
@Dao
interface ItemDao {

    @Query("SELECT * FROM Item")
    suspend fun getAll(): List<Item>

    @Insert
    fun insertAll(item: List<Item>)

    @Insert
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

}