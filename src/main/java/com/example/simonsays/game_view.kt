package com.example.simonsays

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class game_view : AppCompatActivity() {
    private lateinit var buttons: Array<Button>
    private val sequence = mutableListOf<Int>()
    private val userSequence = mutableListOf<Int>()
    private var currentStep = 0
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_view)

        buttons = arrayOf(
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4)
        )

        startNewGame()
    }

    private fun startNewGame() {
        sequence.clear()
        userSequence.clear()
        currentStep = 0
        addNextStep()
        playSequence()
    }

    private fun addNextStep() {
        val next = (1..4).random()
        sequence.add(next)
    }

    private fun playSequence() {
        var delay = 0L
        sequence.forEach { step ->
            handler.postDelayed({
                highlightButton(step)
            }, delay)
            delay += 1000 // Adjust delay time as needed
        }
    }

    private fun highlightButton(buttonIndex: Int) {
        val button = buttons[buttonIndex - 1]
        val originalColor = when (buttonIndex) {
            1 -> Color.RED
            2 -> Color.BLUE
            3 -> Color.GREEN
            4 -> Color.YELLOW
            else -> Color.GRAY
        }
        button.setBackgroundColor(Color.WHITE)
        button.setTextColor(ContextCompat.getColor(this,R.color.black))
        handler.postDelayed({
            button.setBackgroundColor(originalColor)
            button.setTextColor(ContextCompat.getColor(this,R.color.white))
        }, 500)
    }

    private fun onButtonClicked(buttonIndex: Int) {
        userSequence.add(buttonIndex)
        if (userSequence[currentStep] != sequence[currentStep]) {
            Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show()
            startNewGame()
            return
        }
        highlightButton(buttonIndex)
        currentStep++
        if (currentStep == sequence.size) {
            Toast.makeText(this, "Well done! Get ready for the next round.", Toast.LENGTH_SHORT).show()
            userSequence.clear()
            currentStep = 0
            addNextStep()
            playSequence()
        }
    }

    private fun setupButtons() {
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                onButtonClicked(index + 1)
            }
            val button = buttons[index]
            val originalColor = when (index) {
                0 -> Color.RED
                1 -> Color.BLUE
                2 -> Color.GREEN
                3 -> Color.YELLOW
                else -> Color.GRAY
            }
            button.setBackgroundColor(originalColor)
        }
    }

    override fun onResume() {
        super.onResume()
        setupButtons()
    }
}