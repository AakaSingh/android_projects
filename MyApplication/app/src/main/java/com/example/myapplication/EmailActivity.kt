package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import javax.security.auth.Subject

class EmailActivity : AppCompatActivity() {
    private lateinit var reciever : EditText
    private lateinit var subject : EditText
    private lateinit var body : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)

        reciever = findViewById<EditText>(R.id.email_to)
        subject = findViewById<EditText>(R.id.email_subject)
        body = findViewById<EditText>(R.id.email_body)
        val senderButton = findViewById<Button>(R.id.sender_button)

        senderButton.setOnClickListener(this::senderButtonClicked)
    }

    private fun senderButtonClicked(view: View?){
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra("to",reciever.text.toString())
            putExtra("subject",subject.text.toString())
            putExtra("body", body.text.toString())
        }
        startActivity(intent)
    }
}