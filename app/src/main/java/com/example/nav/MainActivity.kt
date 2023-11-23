package com.example.nav

// ? Imports
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nav.bd.ViewModel
import com.example.nav.ui.theme.NavTheme

/**
 * MainActivity class, where the navController is created and the application routes are defined.
 * Toasts and the database's viewModel are also instantiated here.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavTheme { // Aply the theme, but it doesn't work correctly
                // Create the navController
                val navController = rememberNavController()
                val context = LocalContext.current
                val dao = ViewModel(context.applicationContext as Application).database
                val modifier = Modifier
                // Define the routes
                NavHost(
                    navController = navController,
                    startDestination = "Pantalla1" // First screen
                ) {
                    composable(route = "Pantalla1") { // List of Contacts screen
                        Pantalla1(
                            navController,
                            modifier
                        )
                    }
                    composable(route = "Pantalla2") { // Create Contact screen
                        Pantalla2(
                            navController,
                            dao.contactoDao(),
                            context
                        )
                    }
                }
            }
        }
    }
}
