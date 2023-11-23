package com.example.nav

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.nav.bd.Contacto
import com.example.nav.bd.Dao
import kotlinx.coroutines.runBlocking

val iconosDeContacto = listOf(
    R.drawable.man_icon,
    R.drawable.woman_icon,
    R.drawable.other_icon,
    R.drawable.company_icon,
    R.drawable.robot_icon
)

@Composable
fun Pantalla2(navController: NavController, dao: Dao, context : Context){
    Column() {
        Button(modifier = Modifier.size(75.dp), onClick = { navController.navigate("Pantalla1"){
            popUpTo("Pantalla1"){ inclusive = true}
        } }) {
            Icon(painter = painterResource(id = R.drawable.volver), contentDescription = "Icono Volver")
        }
    }
    val contactos: List<Contacto> = runBlocking {
        dao.mostrar()
    }.sortedBy { it.nombre }

    // Show the contacts in a LazyColumn so it can be scrolled
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(1) { // We'll only have one item of every contact
            for (contacto in contactos) { // For every contact
                Card( // Show a card with the contact's info
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable { // Add a click listener to the card to navigate to the contact's detail
                            navController.navigate("Detalle/${contacto.id}")
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Image( // Image of the contact
                                painter = painterResource(id = iconosDeContacto[contacto.genero]),
                                contentDescription = "Imagen de Contacto",
                                modifier = Modifier
                                    .size(50.dp)
                            )
                            Column( // Column to Stack both the name and the phone
                                modifier = Modifier
                                    .padding(16.dp, 0.dp, 0.dp, 0.dp)
                            ) {
                                Text(
                                    text = contacto.nombre,
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = contacto.tlfn,
                                    fontSize = 16.sp
                                )
                            }
                        }
                        Row {
                            Image( // Image of the call button
                                painter = painterResource(id = R.drawable.whatsapp_gold_icon),
                                contentDescription = "Call",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clickable { // Add a click listener to the image to call the contact using a Intent
                                        val intent = Intent(Intent.ACTION_DIAL)
                                        intent.data = Uri.parse("tel:${contacto.tlfn}")
                                        ContextCompat.startActivity(context, intent, null)
                                    }
                            )
                        }
                    }
                }
            }
            Card( // This card is used to have more space in the list so the AddButton doesn't cover the last contact
                modifier = Modifier
                    .padding(50.dp)
                    .fillMaxWidth()
            ) {}
        }
    }
}