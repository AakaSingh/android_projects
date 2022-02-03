package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var editTextName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day2)

        editTextName = findViewById<EditText>(R.id.name_value)
        editTextName.setText("Hello Your Value Is Here")

        val senderButton = findViewById<Button>(R.id.sender_button)
        //senderButton.setOnClickListener(this)
        senderButton.setOnClickListener(this::senderButtonClicked)
    }

    private  fun senderButtonClicked(view: View?){
        Log.e("this","clicked")
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra("name",editTextName.text.toString())
        }
        startActivity(intent)
    }
    override fun onClick(p0: View?) {
        Log.e("button clicked", "onClick: this action ", )
    }
}