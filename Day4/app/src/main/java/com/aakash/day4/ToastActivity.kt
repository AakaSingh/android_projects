package com.aakash.day4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ToastActivity : AppCompatActivity() {
    private lateinit var toastEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast)
        toastEditText = findViewById(R.id.toast_text)
        val toastButton = findViewById<Button>(R.id.toast_button)
        toastButton.setOnClickListener(this::onToastButtonClicked)
    }

    private fun onToastButtonClicked(view : View){
        Toast.makeText(this, toastEditText.text, Toast.LENGTH_SHORT).show()
    }
}