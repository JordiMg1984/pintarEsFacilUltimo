package com.example.pintaresfacil.database.dao

import androidx.room.*
import com.example.pintaresfacil.models.Client


//Con en Dao nos comunicamos con la BD
@Dao
interface ClientDao {


    @Query("SELECT * FROM Client where email = :email and password = :password")
    suspend fun login(email: String, password: String): Client

    @Query("SELECT * FROM Client ")
    suspend fun getAll(email: String, name:String, surName:String, dni:String,mobile:Int, password: String): Client


    @Insert
    suspend fun insert(client: Client)

    @Update
    suspend fun update(client: Client)

    @Delete
    suspend fun delete(client: Client)

}