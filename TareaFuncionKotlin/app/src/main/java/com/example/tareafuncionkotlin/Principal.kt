package com.example.tareafuncionkotlin

fun main(){
    val persona = Persona("Cristiano Ronaldo","Jugador de futbol", 10000)
    println(persona)
    // Podemos declarar la variable como any y hacemos los cambios que queramos
    /*
     var goles:Any
     goles = 100
     golesCristiano(goles)

         goles = "Hola"
         golesCristiano(goles)
     */
    var goles = 100
    golesCristiano(goles)
}
fun golesCristiano(goles: Int){
    println("El bicho ha metido $goles goles. SIUUUUUUUUUUUUU")
}