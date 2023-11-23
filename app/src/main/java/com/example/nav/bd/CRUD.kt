package com.example.nav.bd

import kotlinx.coroutines.runBlocking

class CRUD {
    companion object{
        fun añadirContacto(nombre : String, apellidos : String, telefono : String, genero : Int, dao: Dao) : Int{
            var creado = 1
            var tlfn = 0
            try {
                tlfn = telefono.toInt()
            }catch (e : NumberFormatException){
                creado = 2
            }
            var contacto =  Contacto(0,"","","",1)
            if (!nombre.isNullOrEmpty() && !apellidos.isNullOrEmpty()){
                contacto =Contacto(0, nombre, apellidos, tlfn.toString(), genero)
                creado = 0
            }
            runBlocking { dao.insertar(contacto)}
            return creado
        }
        fun actualizarContacto(id : Int, nombre : String, apellidos : String, telefono : String, genero : Int, dao: Dao) : Int{
            var actualizado = 1
            var tlfn = 0
            try {
                tlfn = telefono.toInt()
            }catch (e : NumberFormatException){
                actualizado = 2
            }
            var contacto =  Contacto(0,"","","",1)
            if (!nombre.isNullOrEmpty() && !apellidos.isNullOrEmpty()){
                contacto =Contacto(id, nombre, apellidos, tlfn.toString(), genero)
                actualizado = 0
            }
            runBlocking { dao.actualizar(contacto)}
            return actualizado
        }
        fun eliminarContacto(id: Int, dao: Dao) : Int{
            var borrado = 1
            if (buscarPorId(id, dao).equals(true)){
                borrado = 0
                val contacto = Contacto(id, "", "", "", 1)
                runBlocking { dao.borrar(contacto) }
            }
            return borrado
        }
        fun buscarPorId(id: Int, dao : Dao) : Boolean{
            var encontrado = false
            var contactos : MutableList<Contacto>
            runBlocking { contactos = dao.mostrar()}
            for (contacto in contactos){
                if (contacto.id.equals(id)){
                    encontrado = true
                }
            }
            return encontrado
        }
    }
}