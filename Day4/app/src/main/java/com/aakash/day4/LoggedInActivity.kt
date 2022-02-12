package com.aakash.day4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TestLooperManager
import android.widget.TextView

class LoggedInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)

        val loggedInMessage = findViewById<TextView>(R.id.loggedIn_hello)

        val myIntent = intent
        if(myIntent.hasExtra(LoginActivity.EXTRA_FIRST_NAME)){
            loggedInMessage.text = "Hello " + myIntent.getStringExtra(LoginActivity.EXTRA_FIRST_NAME).toString() + " " + myIntent.getStringExtra(LoginActivity.EXTRA_LAST_NAME)
        }
    }
}