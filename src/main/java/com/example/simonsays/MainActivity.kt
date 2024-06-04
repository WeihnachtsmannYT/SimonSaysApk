package com.example.simonsays

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Log.d("SimonSays", "Main started!")

        val button1: Button = findViewById(R.id.button)
        button1.setOnClickListener {
            Log.d("SimonSays", "Button Clicked!")
            startActivity(Intent(this, game_view::class.java))
        }
    }
}