package com.example.nav.bd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(contacto: Contacto)

    @Query("SELECT * FROM Contactos WHERE Id = id")
    suspend fun buscarPorId(id : Int) : Contacto

    @Query("SELECT * FROM Contactos")
    suspend fun mostrar() : MutableList<Contacto>

    @Update
    suspend fun actualizar(contacto: Contacto)

    @Delete
    suspend fun borrar(contacto: Contacto)
}