package com.example.holakotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    var tts: TextToSpeech? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(this,this)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnSpeak).setOnClickListener{speak()}
    }

    /**
     * Cabecera: private fun speak()
     * Descripcion: Este metodo se encarga de que el tts 'hable' el mensaje que se encuentra
     * en el editText
     */
    private fun speak(){
        var message: String = findViewById<TextView>(R.id.editTNombre).text.toString()
        if(message.isEmpty()){
            message = "Ay mi madre el bicho, no has escrito nada"
        }
        tts!!.speak(message,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    /**
     * Cabecera: override fun onInit(status: Int)
     * Descripcion: Este metodo se encarga de comprobar si el dispositivo puede utilizar el tts
     * y de mostrarlo en un textView
     */
    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            findViewById<TextView>(R.id.txtVAviso).text = "El boton de speak se puede utilizar"
            tts!!.setLanguage(Locale("ES"))
            //tts!!.setLanguage(Locale.US)
        }else{
            findViewById<TextView>(R.id.txtVNombre).text = "El boton de speak no se puede utilizar"
        }
    }

    override fun onDestroy() {
        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}