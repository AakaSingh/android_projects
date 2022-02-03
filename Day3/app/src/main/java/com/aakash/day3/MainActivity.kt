package com.aakash.day3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hello = findViewById<TextView>(R.id.main_helloWorld)
        val sendMore = findViewById<Button>(R.id.main_send)
        sendMore.setOnClickListener(this::sendMore)

        hello.setText((R.string.app_name))

        val stringValue = resources.getString(R.string.app_name)
    }

    private fun sendMore(view: View){
        val intent = Intent(this, RecieverActivity::class.java).apply {
            
        }
        startActivity(intent)
    }
}