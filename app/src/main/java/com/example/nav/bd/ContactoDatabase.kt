package com.example.nav.bd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Contacto :: class], version = 1)
abstract class ContactoDatabase : RoomDatabase(){
    abstract fun contactoDao() : Dao
}