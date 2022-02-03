package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_display)

        val reciever = findViewById<TextView>(R.id.email_display_to)
        val subject = findViewById<TextView>(R.id.email_display_subject)
        val body = findViewById<TextView>(R.id.email_display_body)

        val myIntent = intent

        if(intent.hasExtra("to")){
            reciever.text = "Hello " + intent.getStringExtra("to")
            subject.text = intent.getStringExtra("subject")
            body.text = intent.getStringExtra("body")
        }
    }
}