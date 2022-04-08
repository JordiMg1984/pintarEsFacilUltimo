package com.example.pintaresfacil.models

import androidx.room.Entity
import androidx.room.PrimaryKey
//Creamos la tabla Client,ponemos de primaryKey el email y de esta manera ponemos false para que no genere id
@Entity
data class Client(
    @PrimaryKey(autoGenerate = false)
    val email: String,
    val name: String,
    val surName: String,
    val dni: String,
    val mobile: Int,
    val password: String
)

