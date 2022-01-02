package com.example.tareafuncionkotlin

//Una data class no puede ser heredada
data class Persona (val nombre: String, val trabajo: String, val salario: Int) {

    fun animar(){
        println("$nombre grita: SIUUUUUUUUUUUUUUUUUUUUUUUU")
    }
}