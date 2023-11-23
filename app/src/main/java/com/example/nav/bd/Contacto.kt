package com.example.nav.bd

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Contactos")
data class Contacto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id: Int = 0,
    @ColumnInfo(name = "Nombre")
    var nombre : String,

    @ColumnInfo(name = "Apellidos")
    var apellidos: String,

    @ColumnInfo(name = "Telefono")
    var tlfn: String,

    @ColumnInfo(name = "Genero")
    var genero : Int,
)