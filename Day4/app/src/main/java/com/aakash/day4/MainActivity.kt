package com.aakash.day4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toastButton = findViewById<Button>(R.id.main_toast)
        toastButton.setOnClickListener{
            val myToast = Toast.makeText(this,"You Just Clicked The Button",Toast.LENGTH_SHORT)
            myToast.show()
        }
    }
}