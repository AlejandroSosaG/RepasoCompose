package com.example.nav.bd

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.room.Room

/**
 * Class that creates the database's viewModel.
 * @param application The application that uses the database.
 * @return The database's viewModel.
 */
class ViewModel(application: Application) : ViewModel() {
    val database: ContactoDatabase by lazy {
        Room.databaseBuilder(
            application,
            ContactoDatabase::class.java,
            "Contactos"
        ).build()
    }
}