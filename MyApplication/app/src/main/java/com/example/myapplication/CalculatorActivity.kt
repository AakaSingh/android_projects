package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class CalculatorActivity : AppCompatActivity() {
    private lateinit var calculatorDisplay : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        calculatorDisplay = findViewById<TextView>(R.id.calculator_display)
        val buttonValue9 = findViewById<Button>(R.id.calculator_value_9)
        val buttonValue8 = findViewById<Button>(R.id.calculator_value_8)
        val buttonValue7 = findViewById<Button>(R.id.calculator_value_7)
        val buttonValue6 = findViewById<Button>(R.id.calculator_value_6)
        val buttonValue5 = findViewById<Button>(R.id.calculator_value_5)
        val buttonValue4 = findViewById<Button>(R.id.calculator_value_4)
        val buttonValue3 = findViewById<Button>(R.id.calculator_value_3)
        val buttonValue2 = findViewById<Button>(R.id.calculator_value_2)
        val buttonValue1 = findViewById<Button>(R.id.calculator_value_1)
        val buttonValue0 = findViewById<Button>(R.id.calculator_value_0)
        val buttonValueClear = findViewById<Button>(R.id.calculator_value_clear)

        buttonValue0.setOnClickListener(this::buttonClicked)
        buttonValue1.setOnClickListener(this::buttonClicked)
        buttonValue2.setOnClickListener(this::buttonClicked)
        buttonValue3.setOnClickListener(this::buttonClicked)
        buttonValue4.setOnClickListener(this::buttonClicked)
        buttonValue5.setOnClickListener(this::buttonClicked)
        buttonValue6.setOnClickListener(this::buttonClicked)
        buttonValue7.setOnClickListener(this::buttonClicked)
        buttonValue8.setOnClickListener(this::buttonClicked)
        buttonValue9.setOnClickListener(this::buttonClicked)
        buttonValueClear.setOnClickListener(this::buttonClicked)

    }

    private fun buttonClicked(view: View?) {
        val clickedButton : Button =  view as Button
        if(clickedButton.text.toString() == "c"){
            calculatorDisplay.text = ""
        }else{
           calculatorDisplay.text =  calculatorDisplay.text.toString() + clickedButton.text.toString()
        }
    }
}